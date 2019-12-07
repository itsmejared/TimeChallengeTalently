package talently.sevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import talently.model.Product;

@Service
public class ProductService {

	private static List<Product> products;

	public ProductService() {
		products = populateProducts();
	}

	private Product findProductByCode(String code) {
		for (Product product : products) {
			if (product.getCode().equals(code)) {
				return product;
			}
		}
		return null;
	}

	public boolean addProduct(String code, List<Product> basket) {
		Product obj = findProductByCode(code);
		if (obj == null)
			return false;
		basket.add(obj);
		return true;
	}

	private void getVoucherPromotion(List<Product> basket) {
		int counter = 0;
		
		for (int i = 0; i < basket.size(); i++) {
			Product obj = basket.get(i);
			if (obj.getCode().equals("VOUCHER")) {
				counter++;
			}
			if(counter == 3) {
				obj.setPrice(00.00);
				basket.set(i, obj);
				counter=0;
			}
		}
	}

	public String getProductsBasket(List<Product> basket) {
		StringBuilder builder = new StringBuilder("ITEMS: ");
		Double totalPrice = 0.0;
		getTshirtPromotion(basket);
		getVoucherPromotion(basket);
		for (Product product : basket) {
			builder.append(product.getName() + ", ");
			totalPrice += product.getPrice();

		}
		builder.append("\nTOTAL: ").append(totalPrice).append("$");

		return builder.toString();
	}

	public void getTshirtPromotion(List<Product> basket) {
		int counter = 0;
		for (Product product : products) {
			if (product.getCode().equals("TSHIRT")) {
				counter++;
			}
			if (counter > 2) {
				break;
			}
		}

		for (int i = 0; i < basket.size(); i++) {
			Product obj = basket.get(i);
			if (obj.getCode().equals("TSHIRT")) {
				obj.setPrice(19.00);
				basket.set(i, obj);
			}
		}

	}

	private static List<Product> populateProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("VOUCHER", "Cabify Voucher", 5.00));
		products.add(new Product("TSHIRT", "Cabify T-Shirt", 20.00));
		products.add(new Product("MUG", "Cabify Coffee Mug", 7.50));
		return products;
	}

}
