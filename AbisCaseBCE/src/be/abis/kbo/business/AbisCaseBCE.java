package be.abis.kbo.business;

import java.io.IOException;
import java.util.ArrayList;

public class AbisCaseBCE {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		String path = "C:\\Users\\Duser\\Desktop\\AbisCaseBCE\\users.txt";
		//String path = "C:\\Users\\Duser\\Desktop";
		Employee test = new Employee(1,"test","test");
		Employee abdel = new Employee(2,"abdel","abdel");
		FileSystem fs = new FileSystem();
		ArrayList<String> list = fs.getUsers(path);
		try {
			
			fs.addEmployee(test, path);
			fs.addEmployee(abdel, path);
			System.out.println("---");
			for (String string : list) {
				System.out.println(string);
			}
			
			System.out.println("Enter password...");
			String pass = "abdel";
			fs.login(pass, abdel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
