package be.abis.kbo.business;

import java.security.*;

import javax.xml.bind.DatatypeConverter;

public class Employee {
	private String login;
	private String password;
	private long id;
	
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
	
	public boolean checkPassword(String passwordToTest) {
		return this.password.equals(hash(passwordToTest+this.id));
	}
}
