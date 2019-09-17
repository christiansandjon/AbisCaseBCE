package be.abis.casebce.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Named
@SessionScoped
public class Activity implements Serializable {

	private int activityId;
	@JsonFormat(pattern = "dd-MM-yyyy kk:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime start;
	@JsonFormat(pattern = "dd-MM-yyyy kk:mm")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime end;
	private String description;

	@Inject
	private Project project;
	@Inject
	@Named("worker")
	private Worker performer;

	// constructor

	public Activity() {
		super();
	}

	public Activity(LocalDateTime start, LocalDateTime end, String description, Project project, Worker performer) {
		super();
		this.start = start;
		this.end = end;
		this.description = description;
		this.project = project;
		this.performer = performer;
	}

	// getter and setters

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Worker getPerformer() {
		return performer;
	}

	public void setPerformer(Worker performer) {
		this.performer = performer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
}
