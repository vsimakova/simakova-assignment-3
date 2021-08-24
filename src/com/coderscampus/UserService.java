package com.coderscampus;

import java.io.FileNotFoundException;
import java.io.File;

public class UserService {
	private User[] userArray;
	

	public void populateUsers(File fileToRead) throws FileNotFoundException {
		userArray = FileService.readFromFile(userArray, fileToRead);
	}
	
	public User getUserByUsernameAndPassword (String username, String password) {
		
		username = username.toLowerCase();
		
		for (int i = 0; i < userArray.length; i++) {
			if (username.equals(userArray[i].getUsername().toLowerCase()) && password.equals(userArray[i].getPassword())) {
				return userArray[i];
			}
		}
		return null;
	}
	
	public User getUserByUsername (String username) {
		
		username = username.toLowerCase();
		
		for (int i = 0; i < userArray.length; i++) {
			if (username.equals(userArray[i].getUsername().toLowerCase())) {
				return userArray[i];
			}
		}
		return null;
	}
}
