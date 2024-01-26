package com.school.sba.responsedto;

import java.time.LocalTime;

import com.school.sba.enums.ProgramType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AcademicProgramResponse {
    
	 private int programId;
     private ProgramType programType;
    private String programName;
    private LocalTime beginsAt;
    private LocalTime endsAt;
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
    
    
}
