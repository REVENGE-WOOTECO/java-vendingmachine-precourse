package vendingmachine.model;

import java.util.List;

public class ProductService {

	public boolean isEmptyProduct() {
		return remainProductQuantity() == 0;
	}

	private int remainProductQuantity() {
		List<Integer> quantityList = ProductRepo.getInstance().getQuantityList();
		return quantityList.stream().mapToInt(i -> i).sum();
	}

	public boolean isExistProductName(String inputProductName) {
		return ProductRepo.getInstance().getNameList().stream().anyMatch(t -> t.equals(inputProductName));
	}
}
