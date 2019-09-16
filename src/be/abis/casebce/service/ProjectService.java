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
	private WebTarget basicTarget;

	public ProjectService() {
		Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
		this.basicTarget = client.target("http://localhost:9080/trs-api/trs-service").path("projects");
	}

	public List<Project> getProjects() {
		WebTarget target = this.basicTarget;
		List<Project> projects = new ArrayList<Project>();
		try {
			projects = target.request().get(new GenericType<List<Project>>() {
			});
		} catch (WebApplicationException e) {
			Response res = e.getResponse();
			ApiError err = res.readEntity(ApiError.class);
			System.out.println(err.getTitle() + ": " + err.getDescription());
		}
		return projects;
	}
	
	
}
