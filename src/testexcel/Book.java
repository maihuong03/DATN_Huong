package testexcel;

public class Book {
	private Integer id;
    private String title;
    private Integer quantity;
    private Double price;
    private Double totalMoney;
    public Book(int id, String title, int quantity, double price, double totalMoney) {
    	this.id = id;
    	this.quantity = quantity;
    	this.price = price;
    	this.totalMoney = totalMoney;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
   
}
