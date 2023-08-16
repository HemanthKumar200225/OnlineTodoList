package models.todoModels;

public class JobTodo extends Todo{
	String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public JobTodo(String title, String desc, String category) {
		super(title, desc);
		this.category = category;
	}

	@Override
	public String toString() {
		return "JobTodo [category=" + category + ", title=" + title + ", desc=" + desc + "]";
	}

	
}
