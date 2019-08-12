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
import java.util.Scanner;

public class FileSystem {

	private String path;
	private ArrayList<String> userList = new ArrayList();

	public void addEmployee(Employee emp, String path) throws IOException, ClassNotFoundException {
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
		if (!checkUsers(emp, path))
		{
			userList.add(emp.toString());
		}
		Files.write(files, userList);

	}

	public boolean checkUsers(Employee emp, String path) throws ClassNotFoundException, IOException {
		ArrayList<String> list = getUsers(path);

		for (String string : list) {
			if (string.equals(emp.getLogin()))
				return true;
		}

		return false;
	}

	public ArrayList getUsers(String path) throws IOException, ClassNotFoundException {
		/*
		 * Scanner scanner = new Scanner(new File(path)); ArrayList<String> list
		 * = new ArrayList<String>(); while (scanner.hasNext()){
		 * list.add(scanner.next()); } scanner.close(); return list;
		 */

		/*
		 * ObjectInputStream ois = new ObjectInputStream(new
		 * FileInputStream(path)); boolean check = true; while (check) {
		 * Employee emp = (Employee) ois.readObject(); if () check = false; }
		 * 
		 * return null;
		 */

		return userList;
	}

	public void login()
	{
		
		System.out.println("You logged in!");
		System.out.println("You did not logged in :'( ");
	}
}
