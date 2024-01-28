package com.school.sba.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.entity.Subject;
import com.school.sba.exception.AcademicProgamNotFoundException;
import com.school.sba.repository.AcademicProgramRepository;
import com.school.sba.repository.SubjectRepository;
import com.school.sba.requestdto.SubjectRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.responsedto.SubjectResponse;
import com.school.sba.service.SubjectService;
import com.school.sba.util.ResponseStructure;
@Service
public class SubjectImpl implements SubjectService {

	@Autowired
	private AcademicProgramRepository AcademicProgramRepository;

	@Autowired
	private SubjectRepository SubjectRepository;

	@Autowired
	private ResponseStructure<AcademicProgramResponse> responseStructure;

	@Autowired
	private ResponseStructure<List<SubjectResponse>> structure;

	@Autowired
	private AcademicProgramServiceImpl academicProgramServiceImpl;



	private SubjectResponse mapToSubjectResponse(Subject subject)
	{
		 SubjectResponse response=new SubjectResponse();
				response.setSubjectId(subject.getSubjectId());
				response.setSubjectName(subject.getSubjectName());
				return response;

	}
	
  @Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(int programId,
			SubjectRequest subjectRequest)
	{
		return AcademicProgramRepository.findById(programId).map(program->{ //found academic program
			List<Subject>subjects= (program.getSubjects()!= null)?program.getSubjects(): new ArrayList<Subject>();

			//to add new subjects that are specified by the client
			subjectRequest.getSubjectName().forEach(name->{
				boolean isPresent =false;
				for(Subject subject:subjects) {
					isPresent = (name.equalsIgnoreCase(subject.getSubjectNames()))?true:false;
					if(isPresent)break;
				}
				if(!isPresent)subjects.add(iSubjectRepository.findBySubjectNames(name)
						.orElseGet(()-> iSubjectRepository.save(Subject.builder().subjectNames(name).build())));
			});
			//to remove the subjects that are not specified by the client
			List<Subject>toBeRemoved= new ArrayList<Subject>();
			subjects.forEach(subject->{
				boolean isPresent = false;
				for(String name:subjectRequest.getSubjectName()) {
					isPresent=(subject.getSubjectName().equalsIgnoreCase(name))?true :false;
					if(isPresent)break;
				}
				if(!isPresent)toBeRemoved.add(subject);
			});
			subjects.removeAll(toBeRemoved);


			program.setSubjects(subjects);//set subjects list to the academic program
			AcademicProgramRepository.save(program);//saving updated program to the database
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("updated the subject list to academic program");
			responseStructure.setData(academicProgramServiceImpl.mapTo(program));
			return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
		}).orElseThrow(()-> new AcademicProgamNotFoundException("AcademicProgram not found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<List<SubjectResponse>>> findAllSubjects() 
	{
		List<Subject> findAll = SubjectRepository.findAll();

		List<SubjectResponse> collect = findAll.stream()
				.map(u->mapToSubjectResponse(u))
				.collect(Collectors.toList());



		structure.setStatus(HttpStatus.FOUND.value());
		structure.setMessage(" sujects found successfully ");
		structure.setData(collect);

		return new ResponseEntity<ResponseStructure<List<SubjectResponse>>>(structure,HttpStatus.FOUND);
	}


}
