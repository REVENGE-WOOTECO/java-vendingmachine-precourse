package vendingmachine.model;

import java.util.HashMap;

public class CoinService {

	public int getCoinCount(HashMap<Integer, Integer> machineCoin, int coin) {
		return Math.min(UserMoneyRepo.getInstance().getUserMoney() / coin, machineCoin.get(coin));
	}

	public void calcUserMoney(HashMap<Integer, Integer> machineCoin, int coin) {
		UserMoneyRepo.getInstance()
			.setUserMoney(UserMoneyRepo.getInstance().getUserMoney() - coin * getCoinCount(machineCoin, coin));
	}
}
