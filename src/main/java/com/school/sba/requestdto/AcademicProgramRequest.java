package com.school.sba.requestdto;

import java.time.LocalTime;

import com.school.sba.enums.ProgramType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AcademicProgramRequest {
        @NotBlank(message="program type is required") 
	    private ProgramType programType;
	   @NotBlank(message = "program name is required")
        private String programName;
	   @NotNull(message = "biginAt is required")
	   private LocalTime beginsAt;
	   @NotNull(message = "endAt is required")
	    private LocalTime endsAt;
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
