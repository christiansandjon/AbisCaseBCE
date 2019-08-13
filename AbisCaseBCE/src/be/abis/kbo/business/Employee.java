package be.abis.kbo.business;

import java.io.IOException;
import java.io.StringWriter;
import java.security.*;

import org.json.simple.*;

public class Employee {
	private String login;
	private String password;
	private long id;
	private static long lastGeneratedId = -1;
	
	public Employee(long id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public Employee(String login, String password) {
		this(generateNewId(),login,hash(password+lastGeneratedId));
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = hash(password+this.id);
	}

	public long getId() {
		return this.id;
	}
	
	private static String hash(String text) {
		MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(text.getBytes());
        byte[] digest = md.digest();
        String myHash = "";
        for (byte b : digest) {
        	myHash += String.format("%02X", b);
        }

		return myHash.toLowerCase();
	}
	
	private static long generateNewId () {
		if (lastGeneratedId == -1) {
			// get highest generated id from file
		}
		return ++lastGeneratedId;
	}
	
	public boolean checkPassword(String passwordToTest) {
		return this.password.equals(hash(passwordToTest+this.id));
	}
	
	public String generateJSon() throws IOException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("login", this.login);
		jsonObj.put("id", this.id);
		jsonObj.put("password", this.password);
		jsonObj.put("role", EmployeeRole.EMPLOYEE.getId());
		StringWriter out = new StringWriter();
		jsonObj.writeJSONString(out);
		return out.toString();
	}
	
	public WorkingDay registerStartWorkingDay() {
		return new WorkingDay(this);
	}
	
	public void registerCloseWorkingDay(WorkingDay day) {
		if (day.getConcernedEmployee().equals(this)) {
			day.closeWorkingDay();
		} else {
			System.out.println("Impossible to close the working day from another employee");
		}
	}

	@Override
	public int hashCode() {
		return  100 + (int)this.getId() + login.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Employee && this.getId() == ((Employee) obj).getId() && this.getLogin().equals(((Employee) obj).getLogin());
	}
}
