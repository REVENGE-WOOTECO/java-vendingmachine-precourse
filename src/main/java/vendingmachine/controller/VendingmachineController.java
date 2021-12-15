package vendingmachine.controller;

import java.util.ArrayList;
import java.util.HashMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;
import vendingmachine.model.MoneyRepo;
import vendingmachine.model.MoneyService;
import vendingmachine.model.ProductService;

public class VendingmachineController {
	private Validator validator = new Validator();
	private ProductService productService = new ProductService();
	private MoneyService moneyService = new MoneyService();
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

	public boolean isrepayment(int currentMoney) {
		return moneyValidate.isLowPrice(currentMoney) || productValidate.isNoRemainProduct(
			productService.remainProductNumber());
	}

	public void buyProduct(String inputProductName, int userMoney) {
		if (!productValidate.isExistProductName(inputProductName)) {
			throw new IllegalArgumentException();
		}
		if (decuctMoney(inputProductName, userMoney) < 0) {
			throw new IllegalArgumentException();
		}
	}

	public int decuctMoney(String inputProductName, int userMoney) {
		return moneyService.deductMoney(inputProductName, userMoney);
	}

	public HashMap<Integer, Integer> repayment(HashMap<Integer, Integer> machineCoin, int userMoney) {
		HashMap<Integer, Integer> repayment = new HashMap<>();

		repayment.put(Coin.COIN_500.getAmount(),
			Math.min(userMoney / Coin.COIN_500.getAmount(), machineCoin.get(Coin.COIN_500.getAmount())));
		userMoney =
			userMoney - 500*Math.min(userMoney / Coin.COIN_500.getAmount(), machineCoin.get(Coin.COIN_500.getAmount()));
		repayment.put(Coin.COIN_100.getAmount(),
			Math.min(userMoney / Coin.COIN_100.getAmount(), machineCoin.get(Coin.COIN_100.getAmount())));
		userMoney =
			userMoney - 100*Math.min(userMoney / Coin.COIN_100.getAmount(), machineCoin.get(Coin.COIN_100.getAmount()));
		repayment.put(Coin.COIN_50.getAmount(),
			Math.min(userMoney / Coin.COIN_50.getAmount(), machineCoin.get(Coin.COIN_50.getAmount())));
		userMoney =
			userMoney - 50*Math.min(userMoney / Coin.COIN_50.getAmount(), machineCoin.get(Coin.COIN_50.getAmount()));
		repayment.put(Coin.COIN_10.getAmount(),
			Math.min(userMoney / Coin.COIN_10.getAmount(), machineCoin.get(Coin.COIN_10.getAmount())));
		userMoney =
			userMoney - 10*Math.min(userMoney / Coin.COIN_10.getAmount(), machineCoin.get(Coin.COIN_10.getAmount()));

		return repayment;
	}

}
