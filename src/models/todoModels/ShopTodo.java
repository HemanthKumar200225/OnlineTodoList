package models.todoModels;

public class ShopTodo extends Todo{
	String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ShopTodo(String title, String desc, String category) {
		super(title, desc);
		this.category = category;
	}

	@Override
	public String toString() {
		return "ShopTodo [category=" + category + ", title=" + title + ", desc=" + desc + "]";
	}
	
}
