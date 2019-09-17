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
import be.abis.casebce.model.Login;
import be.abis.casebce.model.Worker;

public class WorkerService {
	private WebTarget baseTarget;

	public WorkerService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.baseTarget = client.target("http://localhost:9080/trs-api/trs-service").path("workers");
	}

	public Worker login(Login login) throws Exception {
		Worker worker = null;
		WebTarget target = this.baseTarget.path("login");
		Response res = target.request().post(Entity.entity(login, MediaType.APPLICATION_JSON));
		if (Integer.toString(res.getStatus()).startsWith("2")) {
			worker = res.readEntity(Worker.class);
		} else if (Integer.toString(res.getStatus()).startsWith("4")) {
			ApiError err = res.readEntity(ApiError.class);
			throw new Exception(err.getTitle() + ": " + err.getDescription());
		}
		return worker;
	}

}
