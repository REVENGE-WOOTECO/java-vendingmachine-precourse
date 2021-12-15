package vendingmachine.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class HoldingCoins {
    private final static List<Integer> usableCoinList = Arrays.asList(10, 50, 100, 500);
    private Map<Coin, Integer> coins;

    HoldingCoins(String inputMoney) {
        isValidMoney(inputMoney);
        coins = randomPickCoinsInList(inputMoney);
    }

    private void isValidMoney(String inputMoney) {
        isNumber(inputMoney);
        isPositiveNumber(inputMoney);
    }

    private void isNumber(String inputMoney) {
        if (!inputMoney.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 보유 금액은 숫자여야 합니다.");
        }
    }

    private void isPositiveNumber(String inputMoney) {
        if (Integer.parseInt(inputMoney) < 0) {
            throw new IllegalArgumentException("[ERROR] 보유 금액은 양수여야 합니다.");
        }
    }

    private Map<Coin, Integer> randomPickCoinsInList(String inputMoney) {
        Map<Coin, Integer> randomCoinHashMap = new TreeMap<>();
        int currentMoney = Integer.parseInt(inputMoney);
        while (currentMoney >= usableCoinList.stream()
                .min(Comparator.comparing(coin -> coin))
                .orElseThrow(NoSuchElementException::new)) {
            int pickedCoinValue = Randoms.pickNumberInList(usableCoinList);
            if (currentMoney >= pickedCoinValue) {
                currentMoney -= pickedCoinValue;
                Coin key = getCoinByAmount(pickedCoinValue);
                randomCoinHashMap.put(key, randomCoinHashMap.getOrDefault(key, 0) + 1);
            }
        }
        return randomCoinHashMap;
    }

    private Coin getCoinByAmount(int amount) {
        if (amount == Coin.COIN_500.getAmount()) {
            return Coin.COIN_500;
        }
        if (amount == Coin.COIN_100.getAmount()) {
            return Coin.COIN_100;
        }
        if (amount == Coin.COIN_50.getAmount()) {
            return Coin.COIN_50;
        }
        return Coin.COIN_10;
    }

    public int getCoinCount(Coin coin) {
        return this.coins.getOrDefault(coin, 0);
    }

    public Map<Coin, Integer> getCoins() {
        return new HashMap<>(this.coins);
    }

    public int getSumOfCoins() {
        return coins.entrySet()
                .stream()
                .mapToInt(coin -> coin.getKey().getAmount() * coin.getValue())
                .sum();
    }

    public Map<Coin, Integer> getChanges(InsertedMoney insertedMoney) {
        Map<Coin, Integer> changes = new TreeMap<>();
        for (Map.Entry<Coin, Integer> coinAndCount : coins.entrySet()) {
            Coin coin = coinAndCount.getKey();
            int count = coinAndCount.getValue();
            int possibleCoinCount = getPossibleCoinCount(insertedMoney.getInsertedMoney(), coin.getAmount(), count);
            insertedMoney.calculateChange(possibleCoinCount * coin.getAmount());
            if(possibleCoinCount > 0)
                changes.put(coin, possibleCoinCount);
        }
        return changes;
    }

    public int getPossibleCoinCount(int insertedMoney, int amount, int count) {
        int returnCount = 0;
        while (insertedMoney >= amount) {
            insertedMoney -= amount;
            returnCount += 1;
        }
        return Integer.min(returnCount, count);
    }
}
