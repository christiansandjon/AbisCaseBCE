package be.abis.kbo.business;

import java.time.*;

public class Activity {
	private LocalDateTime startTime;
	private Duration duration;
	private Employee concernedEmployee;
	private Project concernedProject;

	public Activity(Employee concernedEmployee, Project concernedProject, LocalDateTime startTime) {
		this(concernedEmployee, concernedProject, startTime, Duration.ofMinutes(15));
	}

	public Activity(Employee concernedEmployee, Project concernedProject, LocalDateTime startTime,
			Duration duration) {
		this.startTime = startTime;
		this.duration = duration;
		this.concernedEmployee = concernedEmployee;
		this.concernedProject = concernedProject;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		if (this.startTime.getYear() == startTime.getYear()
				&& this.startTime.getDayOfYear() == startTime.getDayOfYear()) {
			this.startTime = startTime;
		}
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
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
