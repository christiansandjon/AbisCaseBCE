package be.abis.kbo.business;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileSystem {

	private String path;
	// private ArrayList<String> userList = new ArrayList();
	private JSONArray userList = new JSONArray();

	public void saveEmployee(Employee emp, String path) throws IOException, ClassNotFoundException {
		// C:\Users\Duser\Desktop\AbisCaseBCE
		// Path files = Paths.get(path);
		/*
		 * PrintWriter out = new PrintWriter(path); out.println(emp.getId());
		 * out.println(emp.getLogin());
		 * 
		 * ObjectOutputStream oos = new ObjectOutputStream(new
		 * FileOutputStream(path)); oos.writeObject(emp); oos.close();
		 */
		// PrintWriter out = new PrintWriter(path);
		Path files = Paths.get(path);
		if (!checkUsers(emp, path)) {
			userList.add(emp.generateJSon());
		}
		Files.write(files, userList);
	}

	public boolean checkUsers(Employee emp, String path) throws ClassNotFoundException, IOException {

		JSONArray list = userList;
		for (Object object : list) {
			System.out.println(object + "<-- Test");
			if (object.equals(emp.getLogin())) {
				return true;
			}
		}

		return false;
		/*
		 * ArrayList<String> list = getUsers(); for (String string : list) {
		 * System.out.println(string + "<-- Test"); if
		 * (string.equals(emp.getLogin())) { return true; } }
		 * 
		 * return false;
		 */
	}

	/**
	 * Convert JSON Object to Employee
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public Employee convertJSONTO_Employee(String s) throws ParseException {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse("[0," + s + "]");
		System.out.println(obj);

		JSONArray array = (JSONArray) obj;

		JSONObject obj2 = (JSONObject)array.get(1);
		String password = obj2.get("password").toString();
		String login = obj2.get("login").toString();
		Employee emp = new Employee(login,password);
		System.out.println(emp.getLogin() + " tested");
		return emp;

	}

	public ArrayList convertJSONArrayTo_Arraylist_Employee() throws ParseException {
		ArrayList<Employee> arrayList = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			arrayList.add(convertJSONTO_Employee((String) userList.get(i)));
		}

		return arrayList;
	}

	public void login(String pass, Employee emp) {
		if (emp.checkPassword(pass)) {
			System.out.println("You logged in " + emp.getLogin() + " !");

			try {
				Employee emp2 = convertJSONTO_Employee(emp.generateJSon());
				System.out.println("This is emp2 --> " + emp2.getLogin());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("You did not logged in :'( ");
		}
	}

	public JSONArray getUserList() {
		return userList;
	}

}
