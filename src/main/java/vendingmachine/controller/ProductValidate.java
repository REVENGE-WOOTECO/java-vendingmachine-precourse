package vendingmachine.controller;

public class ProductValidate {
	public boolean isNoRemainProduct(int remainProductNumber) {
		return remainProductNumber == 0;
	}
}
