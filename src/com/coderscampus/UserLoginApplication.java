package com.coderscampus;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class UserLoginApplication {

	public static void main(String[] args) throws IOException {

		UserService service = new UserService();
		File file = new File("data.txt");
		service.populateUsers(file);
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			
			System.out.println("Enter your username: ");
			String username = sc.next();
			
			System.out.println("Enter your password: ");
			String password = sc.next();

			int choice = -1;
			User loggedUser = service.getUserByUsernameAndPassword(username, password);
			
			if (loggedUser != null) { //valid login
				System.out.println("Welcome " + loggedUser.getName());
				System.out.println("----------");
				
				loggedUser.showOptions();
				
				choice = Integer.parseInt(sc.next());
				while (choice != 4) {
				if (choice == 0 && loggedUser.getRole().equals("super_user")) {
					System.out.println("Which user would you like to login as? (Type in a valid username)");
					String anotherUsername = sc.next();
					loggedUser = service.getUserByUsername(anotherUsername);
					for (int j = 0; j < 5; j++) {
						if (loggedUser != null) {
							
							System.out.println("Welcome " + loggedUser.getName());
							System.out.println("----------");
							loggedUser.showOptions();
							choice = Integer.parseInt(sc.next());
						} else {
							if (j != 4) {
								System.out.println("Invalid login, please try again");
								anotherUsername = sc.next();
							}
						}
					}
					sc.close();
					System.out.println("Too many failed login attempts, you are now locked out.");
					System.exit(0);
					
				} else if (choice == 1) {
					System.out.println("Please type in your new username:");
					String newUsername = sc.next();
					loggedUser.updateUsername(newUsername, file);
					loggedUser.showOptions();
					choice = Integer.parseInt(sc.next());
				} else if (choice == 2) {
					System.out.println("Please type in your new password:");
					String newPassword = sc.next();
					loggedUser.updatePassword(newPassword, file);
					loggedUser.showOptions();
					choice = Integer.parseInt(sc.next());
				} else if (choice == 3) {
					System.out.println("Please type in your new name:");
					String newName = sc.next();
					loggedUser.updateName(newName, file);
					loggedUser.showOptions();
					choice = Integer.parseInt(sc.next());
				} else {
					System.out.println("Invalid input, try again");
					choice = Integer.parseInt(sc.next());
				}
				}
				sc.close();
				System.exit(0);
			} else {
				if (i != 4) {
					System.out.println("Invalid login, please try again");
				}
			}
		}
		sc.close();
		System.out.println("Too many failed login attempts, you are now locked out.");
		System.exit(0);
	}

}