package vendingmachine.view;

import java.util.Map;

public class PrintView {
    public static void requestInitialCoin() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    public static void printSmallChange(Map<Integer, Integer> smallChange) {
        for (Integer coinValue : smallChange.keySet()) {
            System.out.println(coinValue + "원 - " + smallChange.get(coinValue) + "개");
        }
    }

    public static void printCoinWithZeroAmount() {
        System.out.println("\n자판기가 보유한 동전");
    }

    public static void requestItemInformation() {
        System.out.println("\n상품명과 가격, 수량을 입력해 주세요.");
    }

    public static void requestInsertMoney() {
        System.out.println("\n투입 금액을 입력해 주세요.");
    }

    public static void showInsertMoney(int insertMoney) {
        System.out.println("\n투입 금액: " + insertMoney + "원");
    }

    public static void requestItemNameForOrder() {
        System.out.println("구매할 상품명을 입력해 주세요.");
    }

    public static void printResultMoreThanZero() {
        System.out.println("잔돈");
    }

    public static void printNoContain() {
        System.out.println("[ERROR] 없는 Item 입니다. 확인 후 입력해주세요.");
    }

    public static void printNoEnoughMoney() {
        System.out.println("[ERROR] 금액이 부족합니다. ");
    }

    public static void printNoEnoughQuantity() {
        System.out.println("[ERROR] 재고가 부족합니다. ");
    }
}
