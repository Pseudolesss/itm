package application;

public class Product {

	// Attributes
	private String name;
	private String code; // Will be ID
	private float sellingPrice;
	private int stockLevel;
	private String information;
	
	// Constructor
	public Product(String name, String code, float sellingPrice, int stockLevel, String information) {
		super();
		this.name = name;
		this.code = code;
		this.sellingPrice = sellingPrice;
		this.stockLevel = stockLevel;
		this.information = information;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public float getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	
	
	
	
}
