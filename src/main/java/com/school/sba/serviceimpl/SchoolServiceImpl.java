package com.school.sba.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.exception.SchoolNotFoundByIdException;
import com.school.exception.UserNotFoundByIdException;
import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.enums.UserRole;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;


@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private ResponseStructure<SchoolResponse> structure;
	
	@Autowired
	private UserRepository userresRepository;
	
	private SchoolResponse mapToSchoolRespose(School school) {
		SchoolResponse response=new SchoolResponse();
		response.setSchoolId(school.getSchoolId());
		response.setSchoolName(school.getSchoolName());
		response.setEmailId(school.getEmailId());
		response.setContactNo(school.getContactNo());
		response.setAddress(school.getAddress());
		 return response;
	}
private School mapToSchool(SchoolRequest schoolRequest) {
		
		School school=new School();
				school.setSchoolName(schoolRequest.getSchoolName());
				school.setEmailId(schoolRequest.getEmailId());
				school.setContactNo(schoolRequest.getContactNo());
				school.setAddress(schoolRequest.getAddress());
				return school;	
	}
	
 @Override
 public ResponseEntity<ResponseStructure<SchoolResponse>> addSchool(SchoolRequest schoolRequest, int userId) {
	 return userresRepository.findById(userId).map(u->{
		 if(u.getUserrole().equals(UserRole.ADMIN)) {
			 if(u.getSchool()==null) {
				 School sc=mapToSchool(schoolRequest);
				 sc=schoolRepository.save(sc);
				 u.setSchool(sc);
				 userresRepository.save(u);
				 
				 structure.setStatus(HttpStatus.CREATED.value());
				 structure.setMessage("School object created succesfully");
				 structure.setData(mapToSchoolRespose(sc));
				 
				 return new ResponseEntity<ResponseStructure<SchoolResponse>>(structure,HttpStatus.CREATED);
			 }
			 else 
				throw new SchoolNotFoundByIdException("school not created");
			}
			 else
				 throw new RuntimeException();
		 }).orElseThrow(()-> new UserNotFoundByIdException("user not found"));
	}
	

@Override
	public ResponseEntity<ResponseStructure<School>> findSchool(int schoolId) {
		Optional<School> optional = schoolRepository.findById(schoolId);
		if(optional.isPresent()) {
			School schl=optional.get();
			ResponseStructure<School> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("Student Object Found Successfully");
			responseStructure.setData(schl);

			return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.FOUND);

		}else {
			throw new SchoolNotFoundByIdException("School Not Found!!!");
		}
	}
	@Override
	public ResponseEntity<ResponseStructure<School>> updateSchool(int schoolId, School updatedSchool) {
		Optional<School> optional = schoolRepository.findById(schoolId);

		if(optional.isPresent()) {
			School existinStudent = optional.get();
			updatedSchool.setSchoolId(existinStudent.getSchoolId());
			School schl = schoolRepository.save(updatedSchool);

			ResponseStructure<School> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("School Object Updated Successfully");
			responseStructure.setData(schl);

			return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.OK);	
		}else {
			throw new SchoolNotFoundByIdException("School Not Found!!!");
		}
	}
	@Override
	public ResponseEntity<ResponseStructure<School>> deleteSchool(int schoolId) {
		Optional<School> optional = schoolRepository.findById(schoolId);

		if(optional.isPresent()) {
			School schl = optional.get();
			schoolRepository.delete(schl);

			ResponseStructure<School> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("School Object deleted Successfully");
			responseStructure.setData(schl);

			return new ResponseEntity<ResponseStructure<School>>(responseStructure,HttpStatus.OK);
		}else {
			throw new SchoolNotFoundByIdException("School Not Found!!!");
		}
	}

	public ResponseEntity<ResponseStructure<List<School>>> findAll(){
		List<School> sl = schoolRepository.findAll();

		if(sl.isEmpty()) {
			return null;

		}
		else {

            ResponseStructure<List<School>> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("School Objects Found Successfully");
			responseStructure.setData(sl);

			return new ResponseEntity<ResponseStructure<List<School>>>(responseStructure,HttpStatus.FOUND);

		}
	}
	
}