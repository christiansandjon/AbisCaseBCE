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

	public WorkingDay getCurrentWorkingDay(int workerId) throws Exception {
		WebTarget target = this.baseTarget.path("current").path(Integer.toString(workerId));
		WorkingDay workingDay = null;
		try {
			workingDay = target.request().get(WorkingDay.class);
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
		return workingDay;
	}

	public WorkingDay startWorkingDay(WorkingDay workingDay) throws Exception {
		WebTarget target = this.baseTarget.path("start");
		Response res = target.request().put(Entity.entity(workingDay, MediaType.APPLICATION_JSON));
		if (Integer.toString(res.getStatus()).startsWith("2")) {
			workingDay = res.readEntity(WorkingDay.class);
		} else if (Integer.toString(res.getStatus()).startsWith("4")) {
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
		return workingDay;
	}

	public WorkingDay closeWorkingDay(WorkingDay workingDay) throws Exception {
		WebTarget target = this.baseTarget.path("close");
		Response res = target.request().put(Entity.entity(workingDay, MediaType.APPLICATION_JSON));
		if (Integer.toString(res.getStatus()).startsWith("2")) {
			workingDay = res.readEntity(WorkingDay.class);
		} else if (Integer.toString(res.getStatus()).startsWith("4")) {
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
		return workingDay;
	}
}
