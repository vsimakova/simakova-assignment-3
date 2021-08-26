package com.coderscampus;

public class SuperUser extends User {
	SuperUser(String username, String password, String name, String role) {
		super(username, password, name, role);
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void showOptions() {
		System.out.println("Please choose from the following options:");
		System.out.println("(0) Log in as another user");
		System.out.println("(1) Update username");
		System.out.println("(2) Update password");
		System.out.println("(3) Update name");
		System.out.println("(4) Exit");
	}
}
