package com.school.sba.serviceimpl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.repository.ScheduleRepository;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseStructure;
import com.school.exception.*;

import jakarta.validation.Valid;

@Service
public class ScheduleServiceImpl  implements ScheduleService{
	@Autowired
	private ScheduleRepository scheduleRepo;
	@Autowired
	private SchoolRepository schoolRepo;

	@Autowired
	private ResponseStructure<ScheduleResponse> responseStructure;

	@Autowired
	private UserRepository userRepository;
	
	private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
		ScheduleResponse response=new ScheduleResponse();
		response.setScheduleId(schedule.getScheduleId());
		response.setOpensAt(schedule.getOpensAt());
		response.setLunchTime(schedule.getLunchTime());
		response.setClosesAt(schedule.getClosesAt());
		response.setClassHoursPerday(schedule.getClassHoursPerday());
		response.setClassHoursLengthInMinutes((int) schedule.getClassHoursLengthInMinutes().toMinutes());
		response.setBreaktime(schedule.getBreaktime());
		response.setLunchBreakLengthInMinutes((int) schedule.getLunchBreakLengthInMinutes().toMinutes());
		 return response;
	}
	
	private Schedule mapToSchedule(ScheduleRequest scheduleRequest) {
		
		Schedule schedule=new Schedule();
		schedule.setOpensAt(scheduleRequest.getOpensAt());
		schedule.setClassHoursPerday(scheduleRequest.getClassHoursPerday());
		schedule.setClosesAt(scheduleRequest.getClosesAt());
		schedule.setClassHoursLengthInMinutes(Duration.ofMinutes(scheduleRequest.getClassHoursLengthInMinutes()));
		schedule.setBreaktime(scheduleRequest.getBreaktime());
		schedule.setBreakeLengthInMinutes(Duration.ofMinutes(scheduleRequest.getBreakeLengthInMinutes()));
		schedule.setLunchTime(scheduleRequest.getLunchTime());
		schedule.setLunchBreakLengthInMinutes(Duration.ofMinutes(scheduleRequest.getLunchBreakLengthInMinutes()));
		return schedule;
}
	
	
	@Override
  public ResponseEntity<ResponseStructure<ScheduleResponse>> addSchoolSchedule(ScheduleRequest scheduleRequest,
		int schoolId) {
	return schoolRepo.findById(schoolId).map(school -> {
		if (school.getSchedule() == null) {
			Schedule schedule = mapToSchedule(scheduleRequest);
			schedule = scheduleRepo.save(schedule);
			school.setSchedule(schedule);
			schoolRepo.save(school);

			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Schedule created Successfully");
			responseStructure.setData(mapToScheduleResponse(schedule));
			return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new DataAlreadyExistException("Schedule data Alredy Exist");
		}
	}).orElseThrow(() -> new SchoolNotFoundByIdException("School Data not Found to given Id"));
	
}
 @Override
 public ResponseEntity<ResponseStructure<ScheduleResponse>> findScheduleByschool(int schoolId) {
	return schoolRepo.findById(schoolId).map(school -> {
		if (school.getSchedule() != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("schedule data found");
			responseStructure.setData(mapToScheduleResponse(school.getSchedule()));
			return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure, HttpStatus.FOUND);
		} else
			throw new ScheduleNotFoundByIdException("Schedule data Not Found By Id");

	}).orElseThrow(() -> new SchoolNotFoundByIdException("School Data not Found to given Id"));
	
}
@Override
public ResponseEntity<ResponseStructure<ScheduleResponse>> updateById(int scheduleId,
		@Valid ScheduleRequest scheduleRequest) {
	return scheduleRepo.findById(scheduleId).map(schedule -> {
		Schedule schedule2 = mapToSchedule(scheduleRequest);
		schedule2.setScheduleId(schedule.getScheduleId());
		schedule2 = scheduleRepo.save(schedule2);
		ScheduleResponse scheduleResponse = mapToScheduleResponse(schedule2);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("schedule data Updated Successfully");
		responseStructure.setData(scheduleResponse);
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure, HttpStatus.OK);

	}).orElseThrow(() -> new ScheduleNotFoundByIdException("Schedule data Not Found By Id"));

}
}

	
	

	

