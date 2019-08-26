package be.abis.casebce.model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Activity implements Serializable {

	// fields
	private Date start;
	private Date end;
	@Inject
	private Project project;
	private String description;

	// no arg constructor
	public Activity() {
	}

	// getter and setters
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
