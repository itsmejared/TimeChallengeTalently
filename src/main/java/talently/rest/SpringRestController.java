package talently.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import talently.model.Product;
import talently.sevice.ProductService;

@RestController
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class SpringRestController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/basket", method = RequestMethod.POST)
	public ResponseEntity<String> createBasket(HttpSession httpSession) {
		Object ses = httpSession.getAttribute("basket");
		if (ses != null) {
			return new ResponseEntity<String>("Basket could't be created because it already exists.",
					HttpStatus.BAD_REQUEST);
		}
		List<Product> basket = new ArrayList<Product>();
		httpSession.setAttribute("basket", basket);

		return new ResponseEntity<String>("Basket was created sucessfully.", HttpStatus.OK);
	}

	@RequestMapping(value = "/basket", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBasket(HttpSession httpSession) {
		httpSession.removeAttribute("basket");

		return new ResponseEntity<String>("Basket was deleted sucessfully.", HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addBasket", method = RequestMethod.POST)
	public ResponseEntity<String> addProductBasket(@RequestBody Product product, HttpSession httpSession) {
		if (product == null || product.getCode() == null || "".equals(product.getCode())) {
			return new ResponseEntity<String>("Product code is required.", HttpStatus.BAD_REQUEST);
		}

		Object ses = httpSession.getAttribute("basket");
		if (ses == null) {
			return new ResponseEntity<String>("Basket must be created first.", HttpStatus.BAD_REQUEST);
		}
		List<Product> basket = (List<Product>) ses;
		if (!productService.addProduct(product.getCode(), basket)) {
			return new ResponseEntity<String>("Product code doesn't exist.", HttpStatus.BAD_REQUEST);
		}
		httpSession.setAttribute("basket", basket);

		return new ResponseEntity<String>("Product was added sucessfully.", HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/totalBasket", method = RequestMethod.GET)
	public ResponseEntity<String> getProductByid(HttpSession httpSession) {
		Object ses = httpSession.getAttribute("basket");
		if (ses == null) {
			return new ResponseEntity<String>("Basket must be created first.", HttpStatus.BAD_REQUEST);
		}
		List<Product> basket = (List<Product>) ses;

		return new ResponseEntity<String>(productService.getProductsBasket(basket), HttpStatus.OK);
	}

}
