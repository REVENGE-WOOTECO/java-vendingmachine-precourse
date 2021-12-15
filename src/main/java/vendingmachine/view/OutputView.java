package vendingmachine.view;

import vendingmachine.model.Coin;
import vendingmachine.model.HoldingCoins;

import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printHoldingCoins(HoldingCoins holdingCoins) {
        StringBuilder holdingCoin = new StringBuilder();
        Map<Coin, Integer> coins = holdingCoins.getCoins();
        System.out.println("자판기가 보유한 동전");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getAmount()+"원 - "+ holdingCoins.getCoinCount(coin) + "개");
        }
        System.out.println();
    }

    public static void printRequestMenuMessage() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    }
}
