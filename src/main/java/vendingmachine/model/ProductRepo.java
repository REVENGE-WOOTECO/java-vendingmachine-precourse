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

	private List<String> nameList = new ArrayList<>();
	private List<Integer> priceList = new ArrayList<>();
	private List<Integer> quantityList = new ArrayList<>();

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

	public int priceForProduct(String product){
		int productIndex = nameList.indexOf(product);
		return priceList.get(productIndex);
	}

	@Override
	public String toString() {
		return "ProductRepo{" +
			"nameList=" + nameList.toString() +
			", priceList=" + priceList.toString() +
			", quantityList=" + quantityList.toString() +
			'}';
	}
}
