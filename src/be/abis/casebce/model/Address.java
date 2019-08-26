package be.abis.casebce.model;

public class Address {

	private int number;
	private String street;
	private String zipCode;
	private String town;
	private String country;

	public Address(int number, String street, String zipCode, String town, String country) {
		this.number = number;
		this.street = street;
		this.zipCode = zipCode;
		this.town = town;
		this.country = country;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
