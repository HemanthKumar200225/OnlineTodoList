package services;

import static models.useful.MyScanner.scanInt;

import static models.useful.MyScanner.scanLine;

import models.useful.Pair;
import models.User;

public class UserInfoService {
	
	public void editUserDetails(Pair<Integer, User> current_user) {
		User user = current_user.getVal2();
		Integer user_id = current_user.getVal1();
		String name = user.getName();

		System.out.println("Press 1 if you want to change name");
		int new_option = scanInt();
		scanLine();
		if(new_option==1) {
			System.out.println("\nGive new name");
			String new_name = (String)scanLine();
			if(name==new_name) {
				System.out.println("same as before, no changes done");
			}
			else {
				user.setName(new_name);
				System.out.println("your new name is: "+new_name);
			}
		}
		System.out.println("Press 1 if you want to change password");
		new_option = scanInt();
		scanLine();
		if(new_option==1) {
			System.out.println("\nGive old password");
			String old_password = (String)scanLine();
			System.out.println("\nGive new password");
			String new_password = (String)scanLine();
			if(old_password.equals(new_password)) {
				System.out.println("Password same as before no changes");
			}
			else {
				user.setPassword(new_password);
				System.out.println("Password changed successfully");
			}
		}
		current_user.setPair(user_id, user);
	}
	
	public Pair<Integer, User> run(Pair<Integer, User> current_user) {
		
//		Integer user_id = current_user.getVal1();
		
		boolean run = true;
		System.out.println("\nWelcome to user options!");
		while(run) {
			System.out.println("Press 1 to See all user details\n"
					+ "Press 2 to edit User details\n"
					+ "Press 0 to go back to previous menu");
			
			int option = scanInt();
			scanLine();
			try {
				switch (option) {
				case 1: {

					System.out.println("\n\nCurrent_user information is:\n"
							+ "-------------------------------------------");
					System.out.println(current_user.getVal2().toString());
					System.out.println("-------------------------------------------");
					break;
				}
				case 2: {
					editUserDetails(current_user);
					break;
				}
				case 0: {
					run=false;
					
					break;
				}
				default:
//					sc.close();
					throw new IllegalArgumentException("Unexpected value: " + option);
				}
			} catch (IllegalArgumentException e){
		          System.out.print("Your Input can only be an integer\nWithin the range of 0-2(0,1,2)!\\n\\nPlease try again!\\n\\n");
		    }
			
		}
//		sc.close();
	
		return current_user;
	}
	
}
