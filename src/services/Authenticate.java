package services;

import java.util.*;

import models.useful.Pair;
import models.User;

public class Authenticate {
	User defaultUsr = new User("Hemanth", "1234");
	
	public HashMap<Integer, User> users_list = new HashMap<Integer, User>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put(1, defaultUsr);
	}};
	Integer next_userId= 0;
	
//	public Pair<Integer, User> signInProcess(){
//		
//		Pair<Integer, User> current_user = new Pair<Integer, User>();
//		
//		Scanner sc = new Scanne(System.in);
//		
//		System.out.println("Give your user id:");
//		int user_id = sc.nextInt();
//		System.out.println("Give your password:");
//		String password = (String)sc.next();
//
//		sc.close();
//		
//		current_user = signIn(user_id, password);
//		System.out.println(current_user.toString());
//		
//		return current_user;
//	}
	
	public Pair<Integer, User> signIn(Integer user_id, String password) {
		
		if(users_list.get(user_id) == null)
			System.out.println("The userId donot exist in database!\nGo to Register Option");
		
		else if(users_list.get(user_id).getPassword().equals(password)) {
			System.out.println("\nSuccefull sign in\n");
			Pair<Integer, User> p = new Pair<Integer, User>();
			p.setPair(user_id, users_list.get(user_id));
//			System.out.println("printing the pair..."+p.getValue());
			
			p.toString();
			return p;
		}
		else
			System.out.println("Wrong userId or Password!");
		return null;
	}
	public Pair<Integer, User> registerUser(String name, String password) {
		User new_user = new User(name, password);
		users_list.put(next_userId, new_user);
		Pair<Integer, User> p = new Pair<Integer, User>();
		p.setPair(next_userId, new_user);
		System.out.println("\nSuccefull register\n");
		System.out.println("\nYour user_id is "+next_userId+"\nIt will be used for login!\n");
		next_userId++;
		return p;
	}
}
