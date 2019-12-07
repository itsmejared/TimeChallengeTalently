package talently.model;

public class Product {

	private String code;
	private String name;
	private Double price;

	public Product() {
	}

	public Product(String code, String name, Double price) {
		this.code = code;
		this.name = name;
		this.price = price;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Product other = (Product) obj;

		if (code != other.code)
			return false;

		return true;
	}

}
