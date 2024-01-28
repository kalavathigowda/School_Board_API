package com.school.sba.serviceimpl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.school.sba.entity.ClassHour;
import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.enums.ClassStatus;
import com.school.sba.exception.AcademicProgramNotFoundByIdException;
import com.school.sba.exception.ScheduleNotFoundBySchoolIdException;
import com.school.sba.repository.AcademicProgramRepository;
import com.school.sba.repository.ClassHourRepository;
import com.school.sba.service.ClassHourService;
import com.school.sba.util.ResponseStructure;

@Service
public  class ClassHourServiceImpl implements ClassHourService {
	
	@Autowired
	private AcademicProgramRepository academicProgramRepository;
	@Autowired
	private ClassHourRepository classHourRepository;

	private boolean isBreakTime(LocalDateTime currentTime , Schedule schedule)
	{
		LocalTime breakTimeStart = schedule.getBreaktime();
		LocalTime breakTimeEnd = breakTimeStart.plusMinutes(schedule.getBreakeLengthInMinutes().toMinutes());
		
		return (currentTime.toLocalTime().isAfter(breakTimeStart) && currentTime.toLocalTime().isBefore(breakTimeEnd));

	}
	
	private boolean isLunchTime(LocalDateTime currentTime , Schedule schedule)
	{
		LocalTime lunchTimeStart = schedule.getLunchTime();
		LocalTime lunchTimeEnd = lunchTimeStart.plusMinutes(schedule.getBreakeLengthInMinutes().toMinutes());
		
		return (currentTime.toLocalTime().isAfter(lunchTimeStart) && currentTime.toLocalTime().isBefore(lunchTimeEnd));

	}
	
	@Override
	public ResponseEntity<ResponseStructure<String>> generateClassHourForAcademicProgram(int programId) 
	{
		return academicProgramRepository.findById(programId)
		.map(academicProgarm -> {
			School school = academicProgarm.getSchool();
			Schedule schedule = school.getSchedule();
			if(schedule!=null)
			{
				int classHourPerDay = schedule.getClassHoursPerday();
				int classHourLength = (int) schedule.getClassHoursLengthInMinutes().toMinutes();
				
				LocalDateTime currentTime = LocalDateTime.now().with(schedule.getOpensAt());
				
				LocalTime lunchTimeStart = schedule.getLunchTime();
				LocalTime lunchTimeEnd = lunchTimeStart.plusMinutes(schedule.getBreakeLengthInMinutes().toMinutes());
				LocalTime breakTimeStart = schedule.getBreaktime();
				LocalTime breakTimeEnd = breakTimeStart.plusMinutes(schedule.getBreakeLengthInMinutes().toMinutes());
				
				for(int day = 1 ; day<=6 ; day++)
				{
					for(int hour = 0;hour<classHourPerDay+2;hour++)
					{
						ClassHour classHour = new ClassHour();
						
						if(!currentTime.toLocalTime().equals(lunchTimeStart) && !isLunchTime(currentTime, schedule))
						{
							if(!currentTime.toLocalTime().equals(breakTimeStart) && !isBreakTime(currentTime, schedule))
							{
								LocalDateTime beginsAt = currentTime;
								LocalDateTime endsAt = beginsAt.plusMinutes(classHourLength);
								
								classHour.setBeginsAt(beginsAt);
								classHour.setEndsAt(endsAt);
								classHour.setClassStatus(ClassStatus.NOT_SCHEDULED);
								
								currentTime = endsAt;
							}
							else
							{
								classHour.setBeginsAt(currentTime);
								classHour.setEndsAt(LocalDateTime.now().with(breakTimeEnd));
								classHour.setClassStatus(ClassStatus.BREAK_TIME);
								currentTime = currentTime.plusMinutes(schedule.getBreakeLengthInMinutes().toMinutes());
							}
						}
						else
						{
							classHour.setBeginsAt(currentTime);
							classHour.setEndsAt(LocalDateTime.now().with(lunchTimeEnd));
							classHour.setClassStatus(ClassStatus.LUNCH_TIME);
							currentTime = currentTime.plusMinutes(schedule.getBreakeLengthInMinutes().toMinutes());
						}
						classHour.setAcademicProgram(academicProgarm);
						classHourRepository.save(classHour);
					}
					currentTime = currentTime.plusDays(1).with(schedule.getOpensAt());
				}
	
			}
			else
				throw new ScheduleNotFoundBySchoolIdException("The school does not contain any schedule, please provide a schedule to the school");
			ResponseStructure<String> responseStructure = new ResponseStructure<String>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("classHour generated successfully for the academic progarm");


			return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.CREATED);
		})
		.orElseThrow(() -> new AcademicProgramNotFoundByIdException("Invalid Program Id"));
	}

	

}
