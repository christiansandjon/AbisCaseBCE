package be.abis.casebce.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.WorkingDay;

public class WorkingDayService {
	WebTarget baseTarget;

	public WorkingDayService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.baseTarget = client.target("http://localhost:9080/trs-api/trs-service").path("working-days");
	}

	public WorkingDay getCurrentWorkingDay(int workerId) {
		WebTarget target = this.baseTarget.path("current").path(Integer.toString(workerId));
		WorkingDay workingDay = null;
		try {
			workingDay = target.request().get(WorkingDay.class);
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getTitle() + ": " + err.getDescription());
		}
		return workingDay;
	}

	public WorkingDay startWorkingDay(WorkingDay workingDay) {
		WebTarget target = this.baseTarget.path("start");
		try {
			Response res = target.request().put(Entity.entity(workingDay, MediaType.APPLICATION_JSON));
			workingDay = res.readEntity(WorkingDay.class);
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getTitle() + ": " + err.getDescription());
		}
		return workingDay;
	}
}
