package vendingmachine.model;

public class ProductDTO {
	private final String name;
	private final int price;
	private final int quantity;

	public ProductDTO(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
}
