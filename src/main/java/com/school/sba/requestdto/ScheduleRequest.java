package com.school.sba.requestdto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerday;
	private int classHoursLengthInMinutes;
	private LocalTime breaktime;
	private int breakeLengthInMinutes;
	private LocalTime lunchTime;
	private int lunchBreakLengthInMinutes;
	public LocalTime getOpensAt() {
		return opensAt;
	}
	public void setOpensAt(LocalTime opensAt) {
		this.opensAt = opensAt;
	}
	public LocalTime getClosesAt() {
		return closesAt;
	}
	public void setClosesAt(LocalTime closesAt) {
		this.closesAt = closesAt;
	}
	public int getClassHoursPerday() {
		return classHoursPerday;
	}
	public void setClassHoursPerday(int classHoursPerday) {
		this.classHoursPerday = classHoursPerday;
	}
	public int getClassHoursLengthInMinutes() {
		return classHoursLengthInMinutes;
	}
	public void setClassHoursLengthInMinutes(int classHoursLengthInMinutes) {
		this.classHoursLengthInMinutes = classHoursLengthInMinutes;
	}
	public LocalTime getBreaktime() {
		return breaktime;
	}
	public void setBreaktime(LocalTime breaktime) {
		this.breaktime = breaktime;
	}
	public int getBreakeLengthInMinutes() {
		return breakeLengthInMinutes;
	}
	public void setBreakeLengthInMinutes(int breakeLengthInMinutes) {
		this.breakeLengthInMinutes = breakeLengthInMinutes;
	}
	public LocalTime getLunchTime() {
		return lunchTime;
	}
	public void setLunchTime(LocalTime lunchTime) {
		this.lunchTime = lunchTime;
	}
	public int getLunchBreakLengthInMinutes() {
		return lunchBreakLengthInMinutes;
	}
	public void setLunchBreakLengthInMinutes(int lunchBreakLengthInMinutes) {
		this.lunchBreakLengthInMinutes = lunchBreakLengthInMinutes;
	}
	
	
}
