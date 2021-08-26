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
			String username = sc.nextLine();
			
			System.out.println("Enter your password: ");
			String password = sc.nextLine();
			
			User loggedUser = service.getUserByUsernameAndPassword(username, password);
			
			if (loggedUser != null) { //valid login
				login(loggedUser, sc, file, service);
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
		
	public static void login (User loggedUser, Scanner sc, File file, UserService service) throws IOException {
		for (int i = 0; i < 5; i++) {
			
			if (loggedUser != null) { //valid login
				int choice = -1;
				System.out.println("Welcome " + loggedUser.getName());
				System.out.println("----------");
				
				loggedUser.showOptions();
				
				choice = Integer.parseInt(sc.nextLine());
				while (choice != 4) {
					if (choice == 0 && loggedUser.getRole().equals("super_user")) {
						System.out.println("Which user would you like to login as? (Type in a valid username)");
						String anotherUsername = sc.nextLine();
						loggedUser = service.getUserByUsername(anotherUsername);
						login(loggedUser, sc, file, service);
					} else if (choice == 1) {
						System.out.println("Please type in your new username:");
						String newUsername = sc.nextLine();
						loggedUser.updateUsername(newUsername, file);
						loggedUser.showOptions();
						choice = Integer.parseInt(sc.nextLine());
					} else if (choice == 2) {
						System.out.println("Please type in your new password:");
						String newPassword = sc.nextLine();
						loggedUser.updatePassword(newPassword, file);
						loggedUser.showOptions();
						choice = Integer.parseInt(sc.nextLine());
					} else if (choice == 3) {
						System.out.println("Please type in your new name:");
						String newName = sc.nextLine();
						loggedUser.updateName(newName, file);
						loggedUser.showOptions();
						choice = Integer.parseInt(sc.nextLine());
					} else {
						System.out.println("Invalid input, try again");
						choice = Integer.parseInt(sc.nextLine());
					}
				}
				sc.close();
				System.exit(0);
			} else {
				if (i != 4) {
					System.out.println("Invalid login, please try again");
					String anotherUsername = sc.nextLine();
					loggedUser = service.getUserByUsername(anotherUsername);
				}
			}
		}
		sc.close();
		System.out.println("Too many failed login attempts, you are now locked out.");
		System.exit(0);
	}

}