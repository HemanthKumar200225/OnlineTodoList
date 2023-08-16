package models;

import java.util.*;

import models.todoModels.HomeTodo;
import models.todoModels.JobTodo;
import models.todoModels.ShopTodo;
import models.todoModels.Todo;

//import models.Todo;

import static models.useful.MyScanner.scanInt;
import static models.useful.MyScanner.scanLine;

public class User {
	String name;
	private String password;
	Integer least_priority=1;
	Set<String> todo_titles = new HashSet<>();
	SortedMap<Integer, HashMap<Integer,Todo>> todo_list = new TreeMap<>();;
	HashMap<Integer, Todo> finished_todo = new HashMap<Integer, Todo>();
	Integer next_todoId= 2;
	
//	Constructor
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	
	public User() {
		super();
	}

//	Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public HashMap<Integer, Todo> getfinished_todo() {
		return finished_todo;
	}
	
	public Set<String> getTodo_titles() {
		return todo_titles;
	}
	
//	Functions
	
	
	public void finishTodo(Integer todo_id, Integer priority){
		
//		Adding the todo to finished todos
		finished_todo.put(todo_id, todo_list.get(priority).get(todo_id));
		
//		Removing title from set
		todo_titles.remove(todo_list.get(priority).get(todo_id).getTitle());
		
		if(todo_list.get(priority).containsKey(1)) {
//			Removing todo from todo_list
			todo_list.get(priority).remove(todo_id);
			System.out.println("Finished the todo Successfully!");
			return;
		}
//		Removing todo from todo_list
		todo_list.remove(priority);
		System.out.println("Finished the todo Successfully!");
		return;
	}
	
	public void addTodoProcess() {
		
		System.out.println("Give the title of the todo");
		String title = scanLine();
		System.out.println("Give the description of the todo");
		String desc = scanLine();
		System.out.println("Enter the category number you want to add the todo to\n"
				+ "1. Shop Todo\n"
				+ "2. Home Todo\n"
				+ "3. Job Todo\n");
		Integer option = scanInt();
		scanLine();
		try {
			switch (option) {
			case 1: {
				Todo todo = new ShopTodo(title, desc, "Shop Todo");
				System.out.println("Give the priority of the todo, if no priority enter 0");
				Integer priority = scanInt();
				scanLine();
				if(priority.equals(0)) {
					priority = least_priority;
					least_priority++;
				}
//				System.out.println("addTodoProcess done");
				addTodo(priority, todo);
				break;
			}
			case 2: {
				Todo todo = new HomeTodo(title, desc, "Home Todo");
				System.out.println("Give the priority of the todo, if no priority enter 0");
				Integer priority = scanInt();
				scanLine();
				if(priority.equals(0)) {
					priority = least_priority;
					least_priority++;
				}
//				System.out.println("addTodoProcess done");
				addTodo(priority, todo);
				break;
			}
			case 3: {
				Todo todo = new JobTodo(title, desc, "Job Todo");
				System.out.println("Give the priority of the todo, if no priority enter 0");
				Integer priority = scanInt();
				scanLine();
				if(priority.equals(0)) {
					priority = least_priority;
					least_priority++;
				}
//				System.out.println("addTodoProcess done");
				addTodo(priority, todo);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
        } catch (IllegalArgumentException e){
	          System.out.print("Your Input can only be an integer\nWithin the range of 1-3(1,2,3)!\\n\\nPlease try again!\\n\\n");
	    }
		
	}
	
	public void addTodo(Integer priority, Todo todo) {
//		Checking if this todo title is already there in the todo_titles set

//		if(this.todo_titles!=null) {
			if(todo_titles.contains(todo.getTitle())) {
				System.out.println("Todo with this title already exists!");
				return;
			}
//		}
		
		HashMap<Integer, Todo> new_todo = new HashMap<Integer, Todo>();
		
		new_todo.put(next_todoId, todo);
		next_todoId++;
//		checking if user gave priority to this todo
		if(priority==0) {
			todo_list.put(least_priority, new_todo);
			least_priority++;
		}
		else if(todo_list.containsKey(priority)) {
			todo_list.get(priority).put(next_todoId, todo);
		}
		else {
			todo_list.put(priority, new_todo);
			if(priority>least_priority)
				least_priority = priority+1;
		}
		
//		Adding the title to set
		todo_titles.add(todo.getTitle());
		System.out.println("Added the todo Successfully");
	}
	


	public void editTodo(Integer todo_id, Integer priority) {
		
		
//		Editing title of todo
		System.out.println("Press 1 if you want to edit the title, if no press any key");
//		Scanner sc = new Scanner(System.in);
		int option = scanInt();
		scanLine();
		Todo todo_edit = todo_list.get(priority).get(todo_id);
		if(option==1) {
			System.out.println("Give the title");
			String title = scanLine();
			if(todo_titles.contains(title)) {
				System.out.println("this new title of todo already exists");
			}
			else
				todo_edit.setTitle(title);
		}
		
//		Editing desc of todo
		System.out.println("Press 1 if you want to edit the descrption, if no press enter");
		option = scanInt();
		scanLine();
		if(option==1) {
			System.out.println("Give the descrption");
			String title = scanLine();
			todo_edit.setDesc(title);
		}
		
//		sc.close();
		todo_edit.toString();
	}
	
//	Removal methods
	public Todo removeTodo(Integer todo_id, Integer priority) {
		if(!todo_list.containsKey(priority)) {
			System.out.println("the todo with priority "+priority+" does not exist");
			return null;
		}
		Todo todo = todo_list.get(priority).get(todo_id);
//		Removing title from set
		todo_titles.remove(todo_list.get(priority).get(todo_id).getTitle());
//		Removing todo from todo_list
		todo_list.get(priority).remove(todo_id);
		
		System.out.println("Removed the todo Successfully!");
		
		todo_titles.remove(todo.getTitle());
		if(todo_list.get(priority).isEmpty()) {
			todo_list.remove(priority);
		}
		
		return todo;
		
	}

	public void removeAllTodo() {
		least_priority=0;
		next_todoId=0;
		todo_titles.clear();
		todo_list.clear();
		finished_todo.clear();
		System.out.println("Removed all the todos");
	}
	public void removeAllFinished() {
		
		finished_todo.clear();
		System.out.println("Removed all finished todos");
		
	}

	
	
//	display of todos
	
	public boolean displayUnfinishedTodos() {
		if(!this.todo_list.isEmpty()) {
			for(Map.Entry<Integer, HashMap<Integer, Todo>> entry : todo_list.entrySet()) {

				System.out.println("--------------------------------");
				int prioroty = entry.getKey();
	            HashMap<Integer, Todo> innerMap = entry.getValue();
	
	            System.out.println("prioroty: " + prioroty);
	            for (Map.Entry<Integer, Todo> innerEntry : innerMap.entrySet()) {
	                int todo_id = innerEntry.getKey();
	                Todo todo = innerEntry.getValue();
	                System.out.println("-----------------------------------------------------------------");
	                System.out.println("todo_id: " + todo_id + ", Todo: " + todo.toString());
	                System.out.println("-----------------------------------------------------------------");
	            }
	            System.out.println();
			}
			return true;
		}
		else {
			System.out.println("Nothing to show");
            System.out.println("-----------------------------------------------------------------");
			return false;
		}
//		return false;
	}
	public boolean displayFinishedTodos() {
		if(!this.finished_todo.isEmpty()) {
			for (Map.Entry<Integer, Todo> entry : finished_todo.entrySet()) {
	            int todo_id = entry.getKey();
	            Todo todo = entry.getValue();
	            System.out.println("todo_id: " + todo_id + ", Todo: " + todo.toString());
	        }
			return true;
		}
		else {
			System.out.println("Nothing to show");
            System.out.println("-----------------------------------------------------------------");
			return false;
		}
	}
	public void displayAllTodos() {
		
		if(!this.todo_list.isEmpty()) {

	    System.out.println("-----------------------------------------------------------------");
		System.out.println("						Unfinished todo tasks					 ");
        System.out.println("-----------------------------------------------------------------");
//				+ "--------------------------------------------------------");
		displayUnfinishedTodos();
		}
		else
			System.out.println("--------------------------------------------------------\n"
					+ 		   "			No unfinished todo tasks here				/n"+
							   "--------------------------------------------------------");
		
		if(!this.finished_todo.isEmpty()) {
			System.out.println("\n\n"
					+ "--------------------------------------------------------/n"
					+ "						Finished todo tasks:				\n"
					+ "--------------------------------------------------------");
			displayFinishedTodos();
		}
		else
			System.out.println("--------------------------------------------------------\n"
					+ 		   "				No finished todo tasks here"
					+ 		   "--------------------------------------------------------");
	}

//	to string
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
}