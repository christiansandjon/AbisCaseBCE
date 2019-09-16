package be.abis.casebce.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.abis.casebce.model.Activity;
import be.abis.casebce.model.Project;
import be.abis.casebce.model.Worker;
import be.abis.casebce.service.ActivityService;
import be.abis.casebce.service.WorkerService;

@Named
@SessionScoped
public class ActivityController implements Serializable {
	private static final long serialVersionUID = 3686922158514988637L;

	@Inject
	private Activity currentActivity;
	@Inject
	@Named("worker")
	Worker performer;
	private List<Activity> displayedActivities;
	private List<Project> potentialProjects;
	
	private ActivityService activityService = new ActivityService();
	private WorkerService workerService = new WorkerService();

//	@EJB(name = "ActivitySession")
//	private ActivitySessionRemote activitySession;
//
//	@EJB(name = "ProjectSession")
//	private ProjectSessionRemote projectSession;
//
//	@EJB(name = "WorkerSession")
//	private WorkerSessionRemote workerSession;

	@PostConstruct
	public void init() {
//		performer = this.workerSession.getUser();
		this.performer = this.workerService.getUser();
//		this.displayedActivities = this.activitySession.getActivities(performer.getId());
		this.displayedActivities = this.activityService.getActivities(this.performer.getId());
//		this.potentialProjects = this.projectSession.getProjects();
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public Worker getPerformer() {
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
//		Activity newActivity = this.activitySession.createActivity(this.currentActivity);
//		if (newActivity == null) {
//			return "createactivity?faces-redirected=true";
//		}
//		this.currentActivity = newActivity;
		return "activityinfo?faces-redirected=true";
	}

	public String validateEdition() {
		this.currentActivity.setEnd(LocalDateTime.of(this.currentActivity.getStart().getYear(),
				this.currentActivity.getStart().getMonth(), this.currentActivity.getStart().getDayOfMonth(),
				this.currentActivity.getEnd().getHour(), this.currentActivity.getEnd().getMinute()));
//		Activity activity = activitySession.updateActivity(this.currentActivity);
//		if (activity != null) {
//			this.setCurrentActivity(activity);
//			return "activityinfo?faces-redirected=true";
//		}
		try {
			this.activityService.updateActivity(this.currentActivity);
		} catch (Exception e) {
			return "activityedit?faces-redirected=true";
		}
		return "activityinfo?faces-redirected=true";
	}

	public String cancelEdition() {
//		this.setCurrentActivity(activitySession.reuploadActivity(this.getCurrentActivity()));
		this.setCurrentActivity(activityService.getActivity(this.currentActivity.getActivityId()));
		return "activityinfo?faces-redirected=true";
	}

	// activity infos
	public String displayActivityInfo(Activity a) {
		this.currentActivity = a;
		return "activityinfo?faces-redirect=true";

	}
	
	public String displayActivityList() {
//		this.displayedActivities = this.activitySession.getActivities(this.getPerformer().getId());
		this.displayedActivities = this.activityService.getActivities(this.performer.getId());
		return "activitydisplay?faces-redirect=true";
	}

	public String generateNewActivityForm() {

//		this.currentActivity = new Activity();
//		this.currentActivity.setPerformer(this.getPerformer());
//		this.currentActivity.setProject(this.potentialProjects.get(0));

		return "createactivity?faces-redirect=true";
	}

}
