package services;

import static models.useful.MyScanner.scanInt;
import static models.useful.MyScanner.scanLine;

import models.User;
import models.useful.Pair;

public class LoggedIn {
	public Pair<Integer, User> run(Pair<Integer, User> current_user) {
		User user = current_user.getVal2();
		String name = user.getName();
		
		boolean run = true;
		System.out.println("Welcome "+name+"! 😊\n");
		while(run) {
			System.out.println("Press 1 to See all todo options\n"
					+ "Press 2 to See all User options\n"
					+ "Press 0 to Logout");
			
			int option = scanInt();
			scanLine();
			try {
				switch (option) {
				case 1: {
					Pair<Integer, User> post_changes_user = new Pair<Integer, User>();
					TodoServices todo_serv = new TodoServices();
					post_changes_user = todo_serv.run(current_user);
					current_user.setPair(post_changes_user.getVal1(), post_changes_user.getVal2());
					break;
				}
				case 2: {
					Pair<Integer, User> post_changes_user = new Pair<Integer, User>();
					UserInfoService usrinfo = new UserInfoService();
					post_changes_user = usrinfo.run(current_user);
					current_user.setPair(post_changes_user.getVal1(), post_changes_user.getVal2());
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
		          System.out.print("\nYour Input can only be an integer\nWithin the range of 0-2(0,1,2)!\n\nPlease try again!\n\n");
		    }
			
			
		}
//		sc.close();
		return current_user;
	}
}
