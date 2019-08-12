package be.abis.kbo.business;

import java.io.IOException;
import java.util.ArrayList;

public class AbisCaseBCE {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		String path = "C:\\Users\\Duser\\Desktop\\AbisCaseBCE\\users.txt";
		//String path = "C:\\Users\\Duser\\Desktop";
		Employee test = new Employee();
		Employee abdel = new Employee();
		FileSystem fs = new FileSystem();
		ArrayList<String> list = fs.getUsers(path);
		try {
			test.setLogin("test");
			test.setLogin("test");
			test.setPassword("test");
			
			abdel.setLogin("Abdel");
			abdel.setPassword("Abdel");
			
			fs.addEmployee(test, path);
			fs.addEmployee(abdel, path);
			System.out.println("---");
			for (String string : list) {
				System.out.println();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
