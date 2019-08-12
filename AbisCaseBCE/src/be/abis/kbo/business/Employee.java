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
		this.password = hashMD5(password);
	}

	public long getId() {
		return this.id;
	}

	private String hashMD5(String text) {
		text += this.id;
		text += id;
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

	@Override
	public String toString() {
		return id + " " + login;
	}

}
