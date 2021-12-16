package vendingmachine.controller;

import vendingmachine.model.ProductRepo;

public class InputParsers {

	private final Validators validator = new Validators();

	public int parseToNumber(String checkMoney) {
		try {
			return Integer.parseInt(checkMoney);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	public void parseProduct(String products) {
		try {
			doParsing(products);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public void doParsing(String products) {
		String[] productsArray = products.split(";");

		for (String s : productsArray) {
			String product = s.substring(1, s.length() - 1);
			String[] productArray = product.split(",");

			if(!validator.productPriceValidator(Integer.parseInt(productArray[1]))){
				throw new IllegalArgumentException();
			}

			ProductRepo.getInstance().saveProduct(productArray);
		}
	}


}
