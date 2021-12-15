package vendingmachine.controller;

import java.util.ArrayList;
import java.util.HashMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;
import vendingmachine.model.ProductService;

public class VendingmachineController {
	private Validator validator = new Validator();
	private ProductService productService = new ProductService();
	private MoneyValidate moneyValidate = new MoneyValidate();
	private ProductValidate productValidate = new ProductValidate();

	public int inputHoldingAmount(String money) {
		validator.holdingAmountValidator(money);
		return Integer.parseInt(money);
	}

	public HashMap<Integer, Integer> makeCoin(int money) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(Coin.COIN_10.getAmount(), 0);
		map.put(Coin.COIN_50.getAmount(), 0);
		map.put(Coin.COIN_100.getAmount(), 0);
		map.put(Coin.COIN_500.getAmount(), 0);

		while (money != 0) {
			int randomCoin = Randoms.pickNumberInList(new ArrayList<>(map.keySet()));

			if (money >= randomCoin) {
				map.replace(randomCoin, map.get(randomCoin) + 1);
				money = money - randomCoin;
			}
		}
		return map;
	}

	public void inputProducts(String products) {
		validator.productsValidator(products);
		manageProducts(products);
	}

	private void manageProducts(String products) {
		productService.separateProducts(products);
	}

	public int inputUserMoney(String userMoney) {
		validator.userMoneyValidator(userMoney);
		// if(repayment(Integer.parseInt(money))){
		// 	//잔돈 돌려주기 실행
		// }
		return Integer.parseInt(userMoney);
	}

	public boolean repayment(int currentMoney) {
		return moneyValidate.isLowPrice(currentMoney) || productValidate.isNoRemainProduct(
			productService.remainProductNumber());
	}

	public void buyProduct(String inputProductName) {
		if(!productValidate.isExistProductName(inputProductName)){
			throw new IllegalArgumentException();
		};
	}
}
