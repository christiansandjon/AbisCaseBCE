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
	private String firstName;
	private String lastName;
	private double monthlyRate;
	private String bankAccount;
	private String address;
	
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
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	protected String generateJSon(int roleId) throws IOException {
		JSONObject json = new JSONObject();
		json.put("login", this.login);
		json.put("id", this.id);
		json.put("password", this.password);
		json.put("role", roleId);
		if (this.getFirstName() != null) {
			json.put("firstName", this.getFirstName());
		}
		if (this.getLastName() != null) {
			json.put("lastName", this.getLastName());
		}
		if (this.getMonthlyRate() != 0) {
			json.put("monthlyRate", this.getMonthlyRate());
		}
		if (this.getBankAccount() != null) {
			json.put("bankAccount", this.getBankAccount());
		}
		if (this.getAddress() != null) {
			json.put("address", this.getAddress());
		}
		StringWriter out = new StringWriter();
		json.writeJSONString(out);
		return out.toString();
	}
	
	public String generateJSon() throws IOException {
		return generateJSon(EmployeeRole.EMPLOYEE.getId());
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
