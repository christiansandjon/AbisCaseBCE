package be.abis.kbo.business;

import java.time.LocalDateTime;

public class Activity {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Employee concernedEmployee;
	private Project concernedProject;

	public Activity(Employee concernedEmployee, Project concernedProject, LocalDateTime startTime) {
		this.startTime = startTime;
		this.endTime = startTime.plusMinutes(15);
		this.concernedEmployee = concernedEmployee;
		this.concernedProject = concernedProject;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		if (this.startTime.getYear() == startTime.getYear()
				&& this.startTime.getDayOfYear() == startTime.getDayOfYear()) {
			if (this.endTime.isAfter(startTime)) {
				this.startTime = startTime;
			}
		}
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		if (this.endTime.getYear() == endTime.getYear() && this.endTime.getDayOfYear() == endTime.getDayOfYear()) {
			if (this.startTime.isBefore(endTime)) {
				this.endTime = endTime;
			}
		}
	}

	public Project getConcernedProject() {
		return concernedProject;
	}

	public void setConcernedProject(Project concernedProject) {
		this.concernedProject = concernedProject;
	}

	public Employee getConcernedEmployee() {
		return concernedEmployee;
	}
}
