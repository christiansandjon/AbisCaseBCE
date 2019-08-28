package be.abis.casebce.model;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Project implements Serializable {

	// fields
	@Inject
	private Company client;
	@Inject
	private Manager manager;
	private String name;
	private double hourRate;
	private String description;

	// no arg constructor
	public Project() {

	}

	// getters and setters
	public Company getClient() {
		return client;
	}

	public void setClient(Company client) {
		this.client = client;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public double getHourRate() {
		return hourRate;
	}

	public void setHourRate(double hourRate) {
		this.hourRate = hourRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return 100 + (this.getName() + " " + (this.getClient() != null ? this.getClient().getName() : "")).hashCode();
	}

}
