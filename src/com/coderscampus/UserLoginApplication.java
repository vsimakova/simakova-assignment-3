package com.coderscampus;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class UserLoginApplication {

	public static void main(String[] args) throws FileNotFoundException {

		UserService service = new UserService();
		service.ReadFromFile(new File("data.txt"));

		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			
			System.out.println("Enter your username: ");
			String username = sc.next();
			
			System.out.println("Enter your paassword: ");
			String password = sc.next();

			String isUser = service.validateInput(username, password);

			if (isUser != "") {
				System.out.println("Welcome " + isUser);
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
	}

}
