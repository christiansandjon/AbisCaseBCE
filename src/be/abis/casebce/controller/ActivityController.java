package be.abis.casebce.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javax.el.ValueExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.Activity;
import be.abis.casebce.model.Project;
import be.abis.casebce.model.Worker;
import be.abis.casebce.service.ActivityService;
import be.abis.casebce.service.ProjectService;

@Named
@SessionScoped
public class ActivityController implements Serializable {
	private static final long serialVersionUID = 3686922158514988637L;

	@Inject
	private Activity currentActivity;
	@Inject
	@Named("worker")
	private Worker performer;
	private List<Activity> displayedActivities;
	private List<Project> potentialProjects;

	private ActivityService activityService = new ActivityService();
	private ProjectService projectService = new ProjectService();

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public Worker getPerformer() {
		if (this.performer.getId() == 0) {
			ValueExpression vex = FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
					.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{loginController}",
							LoginController.class);
			LoginController controller = (LoginController) vex
					.getValue(FacesContext.getCurrentInstance().getELContext());
			this.performer = controller.getWorker();
		}
		return performer;
	}

	public void setPerformer(Worker performer) {
		this.performer = performer;
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

	public String createActivitiy() {
		this.currentActivity.setEnd(LocalDateTime.of(this.currentActivity.getStart().getYear(),
				this.currentActivity.getStart().getMonth(), this.currentActivity.getStart().getDayOfMonth(),
				this.currentActivity.getEnd().getHour(), this.currentActivity.getEnd().getMinute()));
		try {
			activityService.addActivity(currentActivity);
		} catch (Exception e) {
			ResourceBundle bundle = ResourceBundle.getBundle("be.abis.casebce.properties.dictionary",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(e.getMessage()), ""));
			return "createactivity";
		}
		return "activityinfo?faces-redirected=true";
	}

	public String validateEdition() {
		this.currentActivity.setEnd(LocalDateTime.of(this.currentActivity.getStart().getYear(),
				this.currentActivity.getStart().getMonth(), this.currentActivity.getStart().getDayOfMonth(),
				this.currentActivity.getEnd().getHour(), this.currentActivity.getEnd().getMinute()));
		try {
			this.activityService.updateActivity(this.currentActivity);
		} catch (Exception e) {
			ResourceBundle bundle = ResourceBundle.getBundle("be.abis.casebce.properties.dictionary",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(e.getMessage()), ""));
			return "activityedit";
		}
		return "activityinfo?faces-redirected=true";
	}

	public String cancelEdition() {
		try {
			this.setCurrentActivity(activityService.getActivity(this.currentActivity.getActivityId()));
		} catch (Exception e) {
			ResourceBundle bundle = ResourceBundle.getBundle("be.abis.casebce.properties.dictionary",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(e.getMessage()), ""));
			return "activityedit";
		}
		return "activityinfo?faces-redirected=true";
	}

	// activity infos
	public String displayActivityInfo(Activity a) {
		this.currentActivity = a;
		return "activityinfo?faces-redirect=true";

	}

	public void displayActivityList() {
		try {
			this.displayedActivities = this.activityService.getActivities(this.getPerformer().getId());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public String generateNewActivityForm() {

		this.currentActivity = new Activity();
		this.currentActivity.setPerformer(this.getPerformer());
		this.retrievePotentialProjects();
		this.currentActivity.setProject(this.potentialProjects.get(0));
		return "createactivity?faces-redirect=true";
	}

	public void retrievePotentialProjects() {
		try {
			this.potentialProjects = this.projectService.getProjects();
		} catch (Exception e) {
			ResourceBundle bundle = ResourceBundle.getBundle("be.abis.casebce.properties.dictionary",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString(e.getMessage()), ""));
		}
	}
}
