package vendingmachine.model;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
	private ProductRepo() {
	}

	private static ProductRepo instance = new ProductRepo();

	public static ProductRepo getInstance() {
		return instance;
	}

	private final List<String> nameList = new ArrayList<>();
	private final List<Integer> priceList = new ArrayList<>();
	private final List<Integer> quantityList = new ArrayList<>();

	public void saveProduct(ProductDTO dto) {
		nameList.add(dto.getName());
		priceList.add(dto.getPrice());
		quantityList.add(dto.getQuantity());
	}

	public List<Integer> getPriceList() {
		return priceList;
	}

	public List<Integer> getQuantityList() {
		return quantityList;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public int quantityforName(String productName) {
		int productIndex = nameList.indexOf(productName);
		return quantityList.get(productIndex);
	}

	public int priceForName(String productName) {
		int productIndex = nameList.indexOf(productName);
		return priceList.get(productIndex);
	}

	public void reduceProduct(String inputProductName) {
		int productIndex = ProductRepo.getInstance().getNameList().indexOf(inputProductName);
		List<Integer> quantityList = ProductRepo.getInstance().getQuantityList();
		quantityList.set(productIndex, quantityList.get(productIndex) - 1);
	}

	public void saveProduct(String[] productArray) {
		ProductDTO productDTO = new ProductDTO(productArray[0], Integer.parseInt(productArray[1]),
			Integer.parseInt(productArray[2]));
		ProductRepo.getInstance().saveProduct(productDTO);
	}

}
