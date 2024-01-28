package com.school.sba.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.enums.ProgramType;
import com.school.sba.exception.DataAlreadyExistException;
import com.school.sba.exception.InvalidProgramTypeException;
import com.school.sba.exception.SchoolNotFoundByIdException;
import com.school.sba.repository.AcademicProgramRepository;
import com.school.sba.repository.ScheduleRepository;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.service.AcademicProgramService;
import com.school.sba.util.ResponseEntityProxy;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AcademicProgramServiceImpl implements AcademicProgramService{

	@Autowired
	private AcademicProgramRepository AcademicProgramRepository;


	@Autowired
	private SchoolRepository SchoolRepository;


	@Autowired
	private ResponseStructure<AcademicProgramResponse> responseStructure;

	@Autowired
	private ResponseStructure<List<AcademicProgramResponse>> ListResponseStructure;

	private AcademicProgram mapToAcademicProgram(@Valid AcademicProgramRequest academicProgramRequest) 
	{
		AcademicProgram academicProgram=new  AcademicProgram();
		academicProgram.setProgramType(ProgramType.valueOf(academicProgramRequest.getProgramType().toUpperCase()));
		academicProgram.setProgramName(academicProgramRequest.getProgramName());
		academicProgram.setBeginsAt(academicProgramRequest.getBeginsAt());
		academicProgram.setEndsAt(academicProgramRequest.getEndsAt());
		return  academicProgram;
	}
	
	private AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram) {
		AcademicProgramResponse response=new AcademicProgramResponse();
		response.setProgramId(academicProgram.getProgramId());
		response.setProgramName(academicProgram.getProgramName());
		response.setProgramType(academicProgram.getProgramType());
		response.setBeginsAt(academicProgram.getBeginsAt());
		response.setEndsAt(academicProgram.getEndsAt());
        return response;
	}


	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> saveAcademicProgram(int schoolId,
			AcademicProgramRequest academicProgramRequest) {

		School school = SchoolRepository.findById(schoolId)
				.orElseThrow(()-> new SchoolNotFoundByIdException("school not found"));

		AcademicProgram academicProgram = AcademicProgramRepository.save(mapToAcademicProgram(academicProgramRequest));
		school.getaList().add(academicProgram);
		academicProgram.setSchool(school);

		SchoolRepository.save(school);
		AcademicProgramRepository.save(academicProgram );

		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("AcademicProgram saved successfully");
		responseStructure.setData(mapToAcademicProgramResponse(academicProgram));

		return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAcademicProgram(int schoolId)
	{
		SchoolRepository.findById(schoolId)
		.orElseThrow(()-> new SchoolNotFoundByIdException("school not found"));

		List<AcademicProgram> findAll = AcademicProgramRepository.findAll();
		List<AcademicProgramResponse> collect = findAll.stream()
				.map(u->mapToAcademicProgramResponse(u))
				.collect(Collectors.toList());

		if(findAll.isEmpty())
		{
			ListResponseStructure.setStatus(HttpStatus.FOUND.value());
			ListResponseStructure.setMessage("AcademicProgram is empty");
			ListResponseStructure.setData(collect);
		}

		ListResponseStructure.setStatus(HttpStatus.FOUND.value());
		ListResponseStructure.setMessage("AcademicProgram found successfully");
		ListResponseStructure.setData(collect);

		return new ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>>(ListResponseStructure,HttpStatus.FOUND);
	}

	

	
}
