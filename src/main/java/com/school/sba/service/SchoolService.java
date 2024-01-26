package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.util.ResponseStructure;

public interface SchoolService {

    ResponseEntity<ResponseStructure<School>> findSchool(int id);

    public ResponseEntity<ResponseStructure<School>> updateSchool(int schoolId, School school);

    public ResponseEntity<ResponseStructure<School>> deleteSchool(int schoolId);

    public ResponseEntity<ResponseStructure<List<School>>> findAll();

    ResponseEntity<ResponseStructure<SchoolResponse>> addSchool(SchoolRequest schoolRequest, int userId);
	
}
	

