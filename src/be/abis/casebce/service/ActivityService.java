package be.abis.casebce.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.Activity;

public class ActivityService {
	private WebTarget basicTarget;

	public ActivityService() {
		Client client = ClientBuilder.newClient();
		this.basicTarget = client.target("http://localhost:9080/trs-api/trs-service").path("activities");
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
}
