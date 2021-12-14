package vendingmachine.domain;

import java.util.Map;
import java.util.TreeMap;

import camp.nextstep.edu.missionutils.Randoms;

public class Coins {
    public static final int DEFAULT_VALUE = 0;
    public static final int ADD_VALUE = 1;

    private final Map<Coin, Integer> coins;

    public Coins(int machineMoney) {
        this.coins = createCoins(machineMoney);
    }

    private Map<Coin, Integer> createCoins(int machineMoney) {
        Map<Coin, Integer> coins = initCoins();
        int tempMoney = 0;

        while (machineMoney != tempMoney) {
            int randomCoin = Randoms.pickNumberInList(Coin.getCoinAmountList());
            if (tempMoney + randomCoin > machineMoney) {
                continue;
            }
            tempMoney += randomCoin;
            Coin findCoin = Coin.findByAmount(randomCoin);
            coins.put(findCoin, coins.get(findCoin) + ADD_VALUE);
        }

        return coins;
    }

    private Map<Coin, Integer> initCoins() {
        Map<Coin, Integer> coins = new TreeMap<>();
        coins.put(Coin.COIN_500, DEFAULT_VALUE);
        coins.put(Coin.COIN_100, DEFAULT_VALUE);
        coins.put(Coin.COIN_50, DEFAULT_VALUE);
        coins.put(Coin.COIN_10, DEFAULT_VALUE);
        return coins;
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }
}
