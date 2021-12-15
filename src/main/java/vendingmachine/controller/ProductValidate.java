package vendingmachine.controller;

import vendingmachine.model.ProductRepo;

public class ProductValidate {
	public boolean isNoRemainProduct(int remainProductNumber) {
		return remainProductNumber == 0;
	}
	public boolean isExistProductName(String inputProductName){
		return ProductRepo.getInstance().getNameList().stream().anyMatch(t-> t.equals(inputProductName));
	}
}
