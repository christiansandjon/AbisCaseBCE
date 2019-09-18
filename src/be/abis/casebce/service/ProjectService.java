package be.abis.casebce.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import be.abis.casebce.exception.ApiError;
import be.abis.casebce.model.Project;

public class ProjectService {
	private WebTarget baseTarget;

	public ProjectService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.baseTarget = client.target("http://localhost:9080/trs-api/trs-service").path("projects");
	}

	public List<Project> getProjects() throws Exception {
		WebTarget target = this.baseTarget;
		List<Project> projects = new ArrayList<Project>();
		try {
			projects = target.request().get(new GenericType<List<Project>>() {
			});
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getDescription());
			throw new Exception(err.getTitle());
		}
		return projects;
	}

}
