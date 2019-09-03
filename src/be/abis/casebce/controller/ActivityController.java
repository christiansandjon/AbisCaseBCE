package be.abis.casebce.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.commandbutton.CommandButton;

import be.abis.casebce.model.Activity;
import be.abis.casebce.model.Project;

@Named
@SessionScoped
public class ActivityController implements Serializable {
	private static final long serialVersionUID = 3686922158514988637L;

	@Inject
	private Activity currentActivity;
	private List<Activity> displayedActivities;
	private List<Project> potentialProjects;
	
	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public List<Activity> getDisplayedActivities() {
		return displayedActivities;
	}

	public void setDisplayedActivities(List<Activity> displayedActivities) {
		this.displayedActivities = displayedActivities;
	}

	public List<Project> getPotentialProjects() {
		return potentialProjects;
	}

	public void setPotentialProjects(List<Project> potentialProjects) {
		this.potentialProjects = potentialProjects;
	}

	public void createActivities() {
		displayedActivities.add(this.currentActivity);
	}

	public String validateEdition() {
		this.currentActivity.setEnd(LocalDateTime.of(this.currentActivity.getStart().getYear(),
				this.currentActivity.getStart().getMonth(), this.currentActivity.getStart().getDayOfMonth(),
				this.currentActivity.getEnd().getHour(), this.currentActivity.getEnd().getMinute()));
		return "activityInfo.xhtml?faces-redirected=true";
	}
}
