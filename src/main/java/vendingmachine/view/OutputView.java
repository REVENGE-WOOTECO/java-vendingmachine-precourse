package vendingmachine.view;

import java.util.Map;

import vendingmachine.domain.Coin;

public class OutputView {

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println("\n" + message);
    }

    public static void printInsertMoney(int money) {
        System.out.println("\n투입 금액: " + money + "원");
    }

    public static void printVendingMachineCoins(Map<Coin, Integer> coins) {
        System.out.println("\n자판기가 보유한 동전");
        printCoins(coins);
    }

    public static void printResultCoins(Map<Coin, Integer> coins) {
        System.out.println("잔돈");
        printCoins(coins);
    }

    private static void printCoins(Map<Coin, Integer> coins) {
        if (coins.isEmpty()) {
            System.out.println("반환할 동전이 없습니다.");
            return;
        }

        for (Coin coin : coins.keySet()) {
            System.out.println(coin.getAmount() + "원 - " + coins.get(coin) + "개");
        }
    }
}
