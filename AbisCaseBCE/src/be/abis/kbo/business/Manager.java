package be.abis.kbo.business;

import java.io.IOException;

public class Manager extends Employee {

	public Manager(long id, String login, String password) {
		super(id, login, password);
	}

	public Manager(String login, String password) {
		super(login, password);
	}

	@Override
	public String generateJSon() throws IOException {
		return super.generateJSon(EmployeeRole.MANAGER.getId());
	}

}
