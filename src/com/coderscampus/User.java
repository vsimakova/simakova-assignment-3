package com.coderscampus;
import java.io.File;
import java.io.IOException;

public abstract class User implements UserInterface {
	//instance variables
	private String username;
	private String password;
	private String name;
	protected String role;    //normal_user, super_user
	
	//full constructor
	public User(String username, String password, String name, String role) {
		setUsername(username);
		setPassword(password);
		setName(name);
		this.role = role;
	}
	
	//setters and getters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	
	public abstract void showOptions();
	
	public void updateName(String newName, File file) throws IOException {
		String oldS = getUsername() + ", " + getPassword() + ", ";
		oldS += getName() + ", " + getRole();
		setName(newName);
		String newS = getUsername() + ", " + getPassword() + ", ";
		newS += getName() + ", " + getRole();
		FileService.writeToFile(file, oldS, newS);
		System.out.println("Welcome " + getName());
		System.out.println("----------");
	}
	
	public void updatePassword(String newPassword, File file) throws IOException {
		String oldS = getUsername() + ", " + getPassword() + ", ";
		oldS += getName() + ", " + getRole();
		setPassword(newPassword);
		String newS = getUsername() + ", " + getPassword() + ", ";
		newS += getName() + ", " + getRole();
		FileService.writeToFile(file, oldS, newS);
		System.out.println("Password is successfully updated.");
		System.out.println("----------");
	}
	
	public void updateUsername(String newUsername, File file) throws IOException {
		String oldS = getUsername() + ", " + getPassword() + ", ";
		oldS += getName() + ", " + getRole();
		setUsername(newUsername);
		String newS = getUsername() + ", " + getPassword() + ", ";
		newS += getName() + ", " + getRole();
		FileService.writeToFile(file, oldS, newS);
		System.out.println("Username is successfully updated.");
		System.out.println("----------");
	}
}
