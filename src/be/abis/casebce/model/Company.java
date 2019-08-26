package be.abis.casebce.model;
import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Company implements Serializable {

	// fields
	private String name;
	@Inject
	private Address address;

	// no arg constructor
	public Company() {
	}

	// getter and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
