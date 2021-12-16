package vendingmachine.controller;

import java.util.ArrayList;
import java.util.HashMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.model.Coin;
import vendingmachine.model.CoinService;
import vendingmachine.model.MachineMoneyRepo;
import vendingmachine.model.MoneyService;
import vendingmachine.model.ProductRepo;
import vendingmachine.model.ProductService;
import vendingmachine.model.UserMoneyRepo;

public class VendingMachineController {

	private final Validators validator = new Validators();
	private final InputParsers parser = new InputParsers();
	private final MoneyService moneyService = new MoneyService();
	private final ProductService productService = new ProductService();
	private final CoinService coinService = new CoinService();

	public void setMachineMoney(String money) {
		int machineMoney = parser.parseToNumber(money);
		validator.holdingAmountValidator(machineMoney);
		MachineMoneyRepo.getInstance().setMachineMoney(machineMoney);
	}

	public void setUserMoney(String userMoney) {
		int inputMoney = parser.parseToNumber(userMoney);
		UserMoneyRepo.getInstance().setUserMoney(inputMoney);
	}

	public void convertCoin() {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(Coin.COIN_10.getAmount(), 0);
		map.put(Coin.COIN_50.getAmount(), 0);
		map.put(Coin.COIN_100.getAmount(), 0);
		map.put(Coin.COIN_500.getAmount(), 0);

		while (MachineMoneyRepo.getInstance().getMachineMoney() != 0) {
			int randomCoin = Randoms.pickNumberInList(new ArrayList<>(map.keySet()));

			if (MachineMoneyRepo.getInstance().getMachineMoney() >= randomCoin) {
				map.replace(randomCoin, map.get(randomCoin) + 1);
				MachineMoneyRepo.getInstance()
					.setMachineMoney(MachineMoneyRepo.getInstance().getMachineMoney() - randomCoin);
			}
		}

		MachineMoneyRepo.getInstance().setMachineCoin(map);
	}

	public void inputProducts(String products) {
		parser.parseProduct(products);
	}

	public boolean isRepayment() {
		return moneyService.isLowPrice() || productService.isEmptyProduct();
	}

	public void buyProduct(String inputProductName) {

		if (!productService.isExistProductName(inputProductName)) {
			throw new IllegalArgumentException();
		}

		if (ProductRepo.getInstance().quantityForName(inputProductName) == 0
			|| ProductRepo.getInstance().priceForName(inputProductName) > UserMoneyRepo.getInstance().getUserMoney()) {
			throw new IllegalArgumentException();
		}

		ProductRepo.getInstance().reduceProduct(inputProductName);
		UserMoneyRepo.getInstance().reduceMoney(inputProductName);
	}

	public HashMap<Integer, Integer> repayment(HashMap<Integer, Integer> machineCoin) {
		HashMap<Integer, Integer> repayment = new HashMap<>();
		for (Coin coin : Coin.values()) {
			repayment.put(coin.getAmount(),
				coinService.getCoinCount(machineCoin, coin.getAmount()));
			coinService.calcUserMoney(machineCoin, coin.getAmount());
		}

		return repayment;
	}

}
