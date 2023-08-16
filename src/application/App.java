package application;

import static models.useful.MyScanner.scanLine;


import static models.useful.MyScanner.scanInt;
import static models.useful.MyScanner.scanClose;

import models.useful.Pair;
import models.User;
import models.useful.ConsoleColors;
import services.Authenticate;
import services.LoggedIn;

public class App {
	public static void main(String[] args){
		
		Authenticate a = new Authenticate();
		Pair<Integer, User> current_user = new Pair<Integer, User>();
		boolean run = true;
		System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Hello welcome to the online todo list maker!"+ConsoleColors.RESET );
		while(run) {
			System.out.println("Press 1 to Sign in\nPress 2 to Register\nPress 0 to exit Application");
			
			int option = scanInt();
			scanLine();
			try {
				switch (option) {
				case 1: {
					System.out.println("\nGive your user id:");
					int user_id = scanInt();
					scanLine();
					System.out.println("\nGive your password:");
					String password = scanLine();
//					current_user = a.signIn(user_id, password);

//					Post login application run
					LoggedIn lg = new LoggedIn();
					current_user = lg.run(a.signIn(user_id, password));
					break;
				}
				case 2: {
					System.out.println("\nGive your name:");
					String name = scanLine();
					System.out.println("\nGive your password:");
					String password = scanLine();
					
					LoggedIn lg = new LoggedIn();
					current_user = lg.run(a.registerUser(name, password));
					break;
				}
				case 0: {
					run=false;
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + option);
				}
			} catch (IllegalArgumentException e) {
				System.out.print("Your Input can only be an integer\nWithin the range of 0-2(0,1,2)!\n\nPlease try again!\n\n");
			}
			
			
		}
//		sc.close();
		scanClose();
		System.out.println(ConsoleColors.CYAN_BOLD + "\nAsthavistha "+current_user.getVal2().getName()+"! 😉\n"+ConsoleColors.RESET);
		System.out.println("End of app");
	}
}
