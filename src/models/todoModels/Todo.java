package models.todoModels;

public class Todo {
	String title;
	String desc;
	public Todo(String title, String desc) {
		super();
		this.title = title;
		this.desc = desc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
//	public String getDesc() {
//		return desc;
//	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Todo [title=" + title + ", desc=" + desc + "]";
	}
	
}
