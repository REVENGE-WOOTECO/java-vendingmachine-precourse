package vendingmachine.view;

import vendingmachine.model.Coin;
import vendingmachine.model.HoldingCoins;
import vendingmachine.model.InsertedMoney;

import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printHoldingCoins(HoldingCoins holdingCoins) {
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

    public static void printReturnChangesWithMinimumCount(Map<Coin, Integer> changes) {
        System.out.println("잔돈");
        changes.forEach((coin, count) -> System.out.println(coin.getAmount() + "원 - " + count + "개"));
    }

    public static void printCurrentMoney(InsertedMoney insertedMoney) {
        System.out.println("투입 금액: " + insertedMoney.getInsertedMoney());
    }
}
