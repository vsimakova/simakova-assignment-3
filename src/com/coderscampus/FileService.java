package com.coderscampus;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileService {
	
	public static User [] readFromFile(User[] userArray, File fileToRead) throws FileNotFoundException {
		Scanner sc = new Scanner(fileToRead);
		int count = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			count++;
		}
		sc.close();
		
		userArray = new User [count];
		int idx = 0;
		
		Scanner sc2 = new Scanner(fileToRead);
		while (sc2.hasNextLine()) {
			String userLine = sc2.nextLine();
			String[] arr = userLine.split(",");
			
			for (int i=0; i<arr.length; i++) {
				arr[i] = arr[i].trim();
			}
			if (arr[3].equals("super_user")) {
				SuperUser user = new SuperUser(arr[0], arr[1], arr[2], arr[3]);
				userArray[idx] = user;
				idx++;
			}  
			if (arr[3].equals("normal_user")) {
				NormalUser user = new NormalUser(arr[0], arr[1], arr[2], arr[3]);
				userArray[idx] = user;
				idx++;
			}
		}
		sc2.close();
		
		return userArray;
	}
	
	public static void writeToFile(File file, String oldS, String newS) throws IOException {
		BufferedWriter writer = null;
		BufferedReader reader = null;
		String oldText = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String line = reader.readLine();
			while(line != null) {
				oldText = oldText + line + System.lineSeparator();
				line = reader.readLine();
			}
			
			String newText = oldText.replaceAll(oldS, newS);
			
			
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(newText);
		} finally {
			if (writer != null) writer.close();
			if (reader != null) reader.close();
		}
		sort(file);
	}
	
	private static void sort (File file) throws IOException {
		BufferedWriter writer = null;
		BufferedReader reader = null;
		
		ArrayList<String> superUsers = new ArrayList<String>();
		ArrayList<String> normalUsers = new ArrayList<String>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String currentLine = reader.readLine();
			while (currentLine != null) {
				if (currentLine.indexOf("super_user") != -1) {
					superUsers.add(currentLine);
				} else {
					normalUsers.add(currentLine);
				}
				
				currentLine = reader.readLine();
			}
			Collections.sort(superUsers);
			Collections.sort(normalUsers);
			
			writer = new BufferedWriter(new FileWriter(file));
			for (String line : superUsers) {
				writer.write(line);
				writer.newLine();
			}
			for (String line : normalUsers) {
				writer.write(line);
				writer.newLine();
			}
		} finally {
			if (writer != null) writer.close();
			if (reader != null) reader.close();
		}
	}

}
