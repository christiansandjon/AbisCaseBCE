package be.abis.kbo.business;

public enum EmployeeRole {
	EMPLOYEE("Employee",0),
	ACCOUNTANT("Accountant",1),
	MANAGER("Manager",2);
	
	private String role;
	private int id;

	private EmployeeRole(String role, int id) {
		this.role = role;
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}

	public int getId() {
		return id;
	}
}
