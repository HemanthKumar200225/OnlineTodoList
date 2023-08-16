package models.todoModels;

public class HomeTodo extends Todo{
	String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public HomeTodo(String title, String desc, String category) {
		super(title, desc);
		this.category = category;
	}

	@Override
	public String toString() {
		return "HomeTodo [category=" + category + ", title=" + title + ", desc=" + desc + "]";
	}

	
}
