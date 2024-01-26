package com.school.sba.entity;

import java.time.LocalTime;
import java.util.List;

import com.school.sba.enums.ProgramType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class AcademicProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int programId;
    @Enumerated(EnumType.STRING)
    private ProgramType programType;
    private String programName;
    private LocalTime beginsAt;
    private LocalTime endsAt;
    
    @ManyToOne
    @JoinColumn(name="schoolId")
    private School school;
    
    @ManyToMany(mappedBy = "academicPrograms")
    private List<User> users;
    
    @ManyToMany
	private List<Subject> subjects;

	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public ProgramType getProgramType() {
		return programType;
	}
	public void setProgramType(ProgramType programType) {
		this.programType = programType;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public LocalTime getBeginsAt() {
		return beginsAt;
	}
	public void setBeginsAt(LocalTime beginsAt) {
		this.beginsAt = beginsAt;
	}
	public LocalTime getEndsAt() {
		return endsAt;
	}
	public void setEndsAt(LocalTime endsAt) {
		this.endsAt = endsAt;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
    
}
