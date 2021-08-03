package com.coderscampus;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class UserService {
	private User[] array;
	private String foundUser;
	

	public void ReadFromFile(File fileToRead) throws FileNotFoundException {
		Scanner sc = new Scanner(fileToRead);
		int count = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			count++;
		}
		sc.close();
		
		array = new User [count];
		int idx = 0;
		
		Scanner sc2 = new Scanner(fileToRead);
		while (sc2.hasNextLine()) {
			String userLine = sc2.nextLine();
			String[] arr = userLine.split(",");
			User user = new User(arr[0], arr[1], arr[2]);
			array[idx] = user;
			idx++;
		}
		sc2.close();
	}
	
	public String validateInput (String username, String password) {
		if (isValidUsername(username) && isValidPassword(password)) {
			return foundUser;
		}
		return "";
	}
	
	private boolean isValidUsername(String username) {
		for (int i = 0; i < array.length; i++) {
			String user = array[i].getUsername().toLowerCase();
			if (user.equals(username.toLowerCase())) {
				foundUser = array[i].getName();
				return true;
			}
		}
		foundUser = "";
		return false;
	}
	
	private boolean isValidPassword(String password) {
		for (int i = 0; i < array.length; i++) {
			String pass = array[i].getPassword();
			if (pass.equals(password)) {
				if (foundUser.equals(array[i].getName())) {
					return true;
				}
			}
		}
		foundUser = "";
		return false;
	}
}
