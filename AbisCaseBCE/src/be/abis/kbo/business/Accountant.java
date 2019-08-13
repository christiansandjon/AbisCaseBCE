package be.abis.kbo.business;

public class Accountant extends Employee {

	public Accountant(long id, String login, String password) {
		super(id, login, password);
	}

	public Accountant(String login, String password) {
		super(login, password);
	}

}
