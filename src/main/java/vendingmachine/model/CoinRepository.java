package vendingmachine.model;

import static vendingmachine.utils.Constant.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Randoms;

public class CoinRepository {
    private final Map<Integer, Integer> savedCoin;

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
            .forEach(c -> coinMap.put(c, ZERO_WON));
    }

    private static void makeRandomSavedCoin(Map<Integer, Integer> coinMap, int coin) {
        List<Integer> randomList = Coin.showCoinList();
        while (coin != ZERO_WON) {
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

    public Map<Integer, Integer> getSmallChange(int smallChange) {
        Map<Integer, Integer> changeMap = new LinkedHashMap<>();
        for (int coinValue : savedCoin.keySet()) {
            if (smallChange == ZERO_WON) {
                break;
            }
            int coinCount = savedCoin.get(coinValue);
            if (coinCount == ZERO_COUNT || smallChange % coinValue != 0) {
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
            savedCoin.put(coinValue, ZERO_COUNT);
            changeMap.put(coinValue, coinCount);
            smallChange -= (coinValue * coinCount);
            return smallChange;
        }
        savedCoin.put(coinValue, savedCoin.get(coinValue) - needCount);
        changeMap.put(coinValue, needCount);
        return ZERO_WON;
    }
}
