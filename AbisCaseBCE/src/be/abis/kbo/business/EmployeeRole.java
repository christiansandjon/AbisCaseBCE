package be.abis.kbo.business;

public enum EmployeeRole {
	EMPLOYEE("Employee",0);
	
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
