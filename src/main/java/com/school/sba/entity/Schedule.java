package com.school.sba.entity;

import java.time.Duration;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	private LocalTime opensAt;
	private LocalTime closesAt;
	private int classHoursPerday;
	private Duration classHoursLengthInMinutes;
	private LocalTime breaktime;
	private Duration breakeLengthInMinutes;
	private LocalTime lunchTime;
	private Duration lunchBreakLengthInMinutes;
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
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
	public Duration getClassHoursLengthInMinutes() {
		return classHoursLengthInMinutes;
	}
	public void setClassHoursLengthInMinutes(Duration classHoursLengthInMinutes) {
		this.classHoursLengthInMinutes = classHoursLengthInMinutes;
	}
	public LocalTime getBreaktime() {
		return breaktime;
	}
	public void setBreaktime(LocalTime breaktime) {
		this.breaktime = breaktime;
	}
	public Duration getBreakeLengthInMinutes() {
		return breakeLengthInMinutes;
	}
	public void setBreakeLengthInMinutes(Duration breakeLengthInMinutes) {
		this.breakeLengthInMinutes = breakeLengthInMinutes;
	}
	public LocalTime getLunchTime() {
		return lunchTime;
	}
	public void setLunchTime(LocalTime lunchTime) {
		this.lunchTime = lunchTime;
	}
	public Duration getLunchBreakLengthInMinutes() {
		return lunchBreakLengthInMinutes;
	}
	public void setLunchBreakLengthInMinutes(Duration lunchBreakLengthInMinutes) {
		this.lunchBreakLengthInMinutes = lunchBreakLengthInMinutes;
	}
	

}
