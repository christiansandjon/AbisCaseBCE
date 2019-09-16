package be.abis.casebce.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.Activity;

public class ActivityService {
	private WebTarget basicTarget;

	public ActivityService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.basicTarget = client.target("http://localhost:9080/trs-api/trs-service").path("activities");
	}

	public void addActivity(Activity activity) {
		WebTarget target = this.basicTarget.path("add");
		Response responsePost = target.request().post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if (Integer.toString(responsePost.getStatus()).startsWith("2")) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Activity has been added");
			facesContext.addMessage(null, facesMessage);
		} else if (Integer.toString(responsePost.getStatus()).startsWith("4")) {
			throw new WebApplicationException("Activity exist already");
		}

	}

	public List<Activity> getActivities(int performerId) {
		WebTarget target = this.basicTarget.queryParam("worker-id", performerId);
		List<Activity> activities = new ArrayList<Activity>();
		try {
			activities = target.request().get(new GenericType<List<Activity>>() {
			});
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getTitle() + ": " + err.getDescription());
		}
		return activities;
	}

	public Activity getActivity(int activityId) {
		WebTarget target = this.basicTarget.path(Integer.toString(activityId));
		Activity activity = null;
		try {
			activity = target.request().get(Activity.class);
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getTitle() + ": " + err.getDescription());
		}
		return activity;
	}

	public void updateActivity(Activity activity) throws Exception {
		WebTarget target = this.basicTarget.path(Integer.toString(activity.getActivityId()));
		try {
			target.request().put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			throw new Exception(err.getTitle() + ": " + err.getDescription());
		}
	}
}
