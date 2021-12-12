package vendingmachine.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.view.PrintView;

public class CoinRepository {

    private Map<Integer, Integer> savedCoin;

    private CoinRepository(Map<Integer, Integer> savedCoin) {
        this.savedCoin = savedCoin;
    }

    public static CoinRepository of(Map<Integer, Integer> coinMap, int coin) {
        initialSavedCoin(coinMap);
        makeRandomSavedCoin(coinMap, coin);
        return new CoinRepository(coinMap);
    }

    private static void initialSavedCoin(Map<Integer, Integer> coinMap) {
        Coin.showCoinList()
            .forEach(c -> coinMap.put(c, 0));
    }

    private static void makeRandomSavedCoin(Map<Integer, Integer> coinMap, int coin) {
        List<Integer> randomList = Coin.showCoinList();
        while (coin != 0) {
            int randomCoinValue = Randoms.pickNumberInList(randomList);
            if (coin >= randomCoinValue) {
                coinMap.put(randomCoinValue, coinMap.get(randomCoinValue) + 1);
                coin -= randomCoinValue;
            }
        }
    }

    public Map<Integer, Integer> getSavedCoin() {
        return savedCoin;
    }

    private Map<Integer, Integer> showHaveCoin() {
        Map<Integer, Integer> smallChange = new LinkedHashMap<>();
        for (Integer coinValue : savedCoin.keySet()) {
            if (savedCoin.get(coinValue) != 0) {
                smallChange.put(coinValue, savedCoin.get(coinValue));
            }
        }
        return smallChange;
    }

    public Map<Integer, Integer> showSmallChange(int smallChange) {
        Map<Integer, Integer> changeMap = new LinkedHashMap<>();
        for (int coinValue : savedCoin.keySet()) {
            if (smallChange == 0) {
                break;
            }
            int coinCount = savedCoin.get(coinValue);
            if (coinCount == 0 || smallChange % coinValue != 0) {
                continue;
            }
            smallChange = calculateCoinChange(changeMap, smallChange, coinValue, coinCount);
        }
        makeRandomSavedCoin(savedCoin, smallChange);
        return changeMap;
    }

    private int calculateCoinChange(Map<Integer, Integer> changeMap, int smallChange, int coinValue, int coinCount) {
        int needCount = smallChange / coinValue;
        if (needCount >= coinCount) {
            savedCoin.put(coinValue, 0);
            changeMap.put(coinValue, coinCount);
            smallChange -= (coinValue * coinCount);
            return smallChange;
        }
        savedCoin.put(coinValue, savedCoin.get(coinValue) - needCount);
        changeMap.put(coinValue, needCount);
        return 0;
    }
}
