package be.abis.kbo.business;

import java.security.*;

import javax.xml.bind.DatatypeConverter;

public class Employee {
	private String login;
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = hashMD5(password);
	}
	
	private String hashMD5(String text) {
		MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(text.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return myHash.toLowerCase();
	}
	
	public boolean checkPassword(String passwordToTest) {
		return this.password.equals(hashMD5(passwordToTest));
	}
}
