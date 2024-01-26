package com.school.sba.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.school.sba.responsedto.AcademicProgramResponse;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Component
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
     private int schoolId;
     private String schoolName;
     private long contactNo;
     private String emailId;
     private String address;
	
     @OneToOne
 	private Schedule schedule;
     
     @OneToMany(mappedBy = "school")
 	private List<AcademicProgram> aList;

     public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<AcademicProgram> getaList() {
		return aList;
	}
	public void setaList(List<AcademicProgram> aList) {
		this.aList = aList;
	}
	
	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolName=" + schoolName + ", contactNo=" + contactNo + ", emailId="
				+ emailId + ", address=" + address + "]";
	}
	
}
