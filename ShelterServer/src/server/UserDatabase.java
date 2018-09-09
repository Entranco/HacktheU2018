package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {
	private ArrayList<User> users;
	
	public UserDatabase() {
		createFromText();
	}
	
	private void createFromText() {
		String placeholder = "";
		try(Scanner input = new Scanner(new FileInputStream("src/server/texts/userinfo.txt"))) {
			while(input.hasNextLine()) {
				placeholder = input.nextLine();
				users.add(new User(placeholder.substring(0, placeholder.indexOf(',')), 
						placeholder.substring(placeholder.indexOf(','), placeholder.length())));
			}
		}catch(FileNotFoundException ex) {
			
		}
	}
	
	public String verifyUser(String user, String pass) {
		for(User el: users) {
			if(el.getUsername().equals(user))
				if(el.getPassword().equals(pass))
					return "true";
		}
		return "false";
	}
}
