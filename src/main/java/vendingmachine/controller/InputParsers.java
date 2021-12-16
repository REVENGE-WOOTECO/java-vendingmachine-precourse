package vendingmachine.controller;

import vendingmachine.model.ProductDTO;
import vendingmachine.model.ProductRepo;

public class InputParsers {

	public int parseToNumber(String checkMoney) {
		try {
			return Integer.parseInt(checkMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	public void parseProducts(String products) {
		try {
			doParsing(products);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	private void doParsing(String products) {
		String[] productsArray = products.split(";");

		for (String s : productsArray) {
			String product = s.substring(1, s.length() - 1);
			String[] productArray = product.split(",");
			saveProduct(productArray);
		}

	}

	private void saveProduct(String[] productArray) {
		ProductDTO productDTO = new ProductDTO(productArray[0], Integer.parseInt(productArray[1]),
			Integer.parseInt(productArray[2]));
		ProductRepo.getInstance().saveProduct(productDTO);
	}
}
