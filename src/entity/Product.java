package entity;

public class Product {
	private Product next;
	private Product previous;
	private int identifyCode; 
	private String Name;
	private Double price;
	private String description;
	private int quantity;
	
	public Product getNext() {
		return next;
	}
	public void setNext(Product next) {
		this.next = next;
	}
	public Product getPrevious() {
		return previous;
	}
	public void setPrevious(Product previous) {
		this.previous = previous;
	}
	public int getIdentifyCode() {
		return identifyCode;
	}
	public void setIdentifyCode(int identifyCode) {
		this.identifyCode = identifyCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
