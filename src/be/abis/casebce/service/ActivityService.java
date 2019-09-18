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
	private WebTarget baseTarget;

	public ActivityService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.baseTarget = client.target("http://localhost:9080/trs-api/trs-service").path("activities");
	}

	public void addActivity(Activity activity) throws Exception {
		WebTarget target = this.baseTarget.path("add");
		Response responsePost = target.request().post(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if (Integer.toString(responsePost.getStatus()).startsWith("2")) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Activity has been added");
			facesContext.addMessage(null, facesMessage);
		} else if (Integer.toString(responsePost.getStatus()).startsWith("4")) {
			ApiError err = responsePost.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}

	}

	public List<Activity> getActivities(int performerId) throws Exception {
		WebTarget target = this.baseTarget.queryParam("worker-id", performerId);
		List<Activity> activities = new ArrayList<Activity>();
		try {
			activities = target.request().get(new GenericType<List<Activity>>() {
			});
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
		return activities;
	}

	public Activity getActivity(int activityId) throws Exception {
		WebTarget target = this.baseTarget.path(Integer.toString(activityId));
		Activity activity = null;
		try {
			activity = target.request().get(Activity.class);
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
		return activity;
	}

	public void updateActivity(Activity activity) throws Exception {
		WebTarget target = this.baseTarget.path(Integer.toString(activity.getActivityId()));
		Response res = target.request().put(Entity.entity(activity, MediaType.APPLICATION_JSON));
		if (Integer.toString(res.getStatus()).startsWith("4")) {
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
	}
}
