package com.school.sba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Subject {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
     private String subjectName;
     
     @ManyToMany
     private AcademicProgram academicProgram;
     
	public AcademicProgram getAcademicProgram() {
		return academicProgram;
	}
	public void setAcademicProgram(AcademicProgram academicProgram) {
		this.academicProgram = academicProgram;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
     
     
}
