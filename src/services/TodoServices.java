package services;

import static models.useful.MyScanner.scanLine;



import static models.useful.MyScanner.scanInt;

import models.useful.Pair;
import models.todoModels.Todo;
import models.User;

public class TodoServices {
	
	public void editUnfinished(Pair<Integer, User> current_user) {
		User user = current_user.getVal2();
		Integer user_id = current_user.getVal1();
		System.out.println("What is the priority of the todo you want to edit?");
		Integer priority = scanInt();
		scanLine();
		System.out.println("What is the todo_id of the todo you want to edit?");
		Integer todo_id = scanInt();
		scanLine();
		user.editTodo(todo_id, priority);
		current_user.setPair(user_id, user);
		return;
	}
	
	public void removeUnfinished(Pair<Integer, User> current_user) {
		User user = current_user.getVal2();
		Integer user_id = current_user.getVal1();
		System.out.println("What is the priority of the todo you want to delete?");
		Integer priority = scanInt();
		scanLine();
		System.out.println("What is the todo_id of the todo you want to delete?");
		Integer todo_id = scanInt();
		scanLine();
		Todo todo = user.removeTodo(todo_id, priority);
		if(todo==null) {
			System.out.println("No Todo removed!");
			return;
		}
		System.out.println("Below are the details of removed todos");
		todo.toString();
		current_user.setPair(user_id, user);
	}
	 

	private void finish(Pair<Integer, User> current_user) {
		User user = current_user.getVal2();
		Integer user_id = current_user.getVal1();
		System.out.println("What is the priority of the todo you want to finish?");
		Integer priority = scanInt();
		scanLine();
		System.out.println("What is the todo_id of the todo you want to finish?");
		Integer todo_id = scanInt();
		scanLine();
		user.finishTodo(todo_id, priority);
		current_user.setPair(user_id, user);
		
	}
	
	public Pair<Integer, User> run(Pair<Integer, User> current_user) {
		User user = current_user.getVal2();
		Integer user_id = current_user.getVal1();
		boolean run = true;
		System.out.println("\nWelcome to user options!");
		while(run) {
			System.out.println("Press 1 to See all unfinished todos\n"//done
					+ "Press 2 to edit unfinished todos\n"//done
					+ "Press 3 to See all the finished todos\n"//done
					+ "Press 4 to See all todos\n"//done
					+ "Press 5 to remove an unfinished todo\n"//done
					+ "Press 6 to remove all finished todos\n"//done
					+ "Press 7 to remove all todo\n"//done
					+ "Press 8 to add a todo\n"//done
					+ "Press 9 to finish a todo\n"//done
					+ "Press 0 to go back to previous menu");
			
			int option = scanInt();
			scanLine();
			try {
				switch (option) {
				case 1: {
					user.displayUnfinishedTodos();
					break;
				}
				case 2: {
					if(!user.displayUnfinishedTodos())
						break;
					editUnfinished(current_user);
					break;
				}
				case 3: {
					user.displayFinishedTodos();
					break;
				}
				case 4: {
					user.displayAllTodos();
					break;
				}
				case 5: {
					boolean empty = user.displayUnfinishedTodos();
					if(!empty) {
						System.out.println("Sorry no todos to delete!");
						break;
					}
					removeUnfinished(current_user);
					break;
				}
				case 6: {
					user.removeAllFinished();
					current_user.setPair(user_id, user);
					break;
				}
				case 7: {
					user.removeAllTodo();
					current_user.setPair(user_id, user);
					break;
				}
				case 8: {
					user.addTodoProcess();
					current_user.setPair(user_id, user);
					break;
				}
				case 9: {
					user.displayUnfinishedTodos();
					finish(current_user);
					
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
		          System.out.print("Your Input can only be an integer\nWithin the range of 0-9(0,1,2,3,4,5,6,7,8,9)!\\n\\nPlease try again!\\n\\n");
		    }
			
		}
		
		return current_user;
	}


}
