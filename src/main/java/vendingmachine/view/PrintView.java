package vendingmachine.view;

import java.util.Map;

public class PrintView {
    public static void requestInitialCoin() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    public static void printSmallChange(Map<Integer, Integer> smallChange) {
        System.out.println("\n자판기가 보유한 동전");
        for (Integer coinValue : smallChange.keySet()) {
            System.out.println(coinValue + "원 - " + smallChange.get(coinValue) + "개");
        }
    }

    public static void requestItemInformation() {
        System.out.println("\n상품명과 가격, 수량을 입력해 주세요.");
    }

    public static void requestInsertMoney() {
        System.out.println("\n투입 금액을 입력해 주세요.");
    }

    public static void showInsertMoney(int insertMoney) {
        System.out.println("\n투입 금액 : " + insertMoney + "원");
    }

    public static void requestItemNameForOrder() {
        System.out.println("구매할 상품명을 입력해 주세요.");
    }
}
