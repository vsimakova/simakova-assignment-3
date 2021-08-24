package com.coderscampus;

public class SuperUser extends User {
	SuperUser(String username, String password, String name, String role) {
		super(username, password, name, role);
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void showOptions() {
		System.out.println("(0) Log in as another user");
		super.showOptions();
	}
}
