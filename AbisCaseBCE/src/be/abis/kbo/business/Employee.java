package be.abis.kbo.business;

import java.security.*;

import javax.xml.bind.DatatypeConverter;

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
		return lastGeneratedId++;
	}
	
	public boolean checkPassword(String passwordToTest) {
		return this.password.equals(hash(passwordToTest+this.id));
	}

	@Override
	public String toString() {
		return id + " " + login;
	}

}
