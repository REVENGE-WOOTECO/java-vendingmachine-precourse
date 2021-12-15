package vendingmachine.model;

public class ProductService {
	public void separateProducts(String products) {
		String[] productsArray = products.split(";");

		for (String s : productsArray) {
			String product = s.substring(1, s.length() - 1);
			String[] productArray = product.split(",");
			saveProduct(productArray);
		}

	}

	public void saveProduct(String[] productArray) {
		ProductDTO productDTO = new ProductDTO(productArray[0], Integer.parseInt(productArray[1]),
			Integer.parseInt(productArray[2]));
		ProductRepo.getInstance().saveProduct(productDTO);
	}
}
