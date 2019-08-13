package be.abis.kbo.business;

public class Manager extends Employee {

	public Manager(long id, String login, String password) {
		super(id, login, password);
	}

	public Manager(String login, String password) {
		super(login, password);
	}

}
