package be.abis.casebce.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.Worker;

public class WorkerService {
	private WebTarget basicTarget;

	public WorkerService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.basicTarget = client.target("http://localhost:9080/trs-api/trs-service").path("workers");
	}
	
	public Worker getUser() {
		WebTarget target = this.basicTarget.path(Integer.toString(1));
		Worker worker = null;
		try {
			worker = target.request().get(Worker.class);
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getTitle() + ": " + err.getDescription());
		}
		return worker;
	}
}
