package be.abis.kbo.business;

import java.io.IOException;

public class Accountant extends Employee {

	public Accountant(long id, String login, String password) {
		super(id, login, password);
	}

	public Accountant(String login, String password) {
		super(login, password);
	}

	@Override
	public String generateJSon() throws IOException {
		return super.generateJSon(EmployeeRole.ACCOUNTANT.getId());
	}

}
