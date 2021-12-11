package vendingmachine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vendingmachine.model.CoinRepository;
import vendingmachine.model.Item;
import vendingmachine.view.InputView;
import vendingmachine.view.PrintView;

public class VendingMachine {

    private CoinRepository coinRepository;
    private List<Item> items;

    public VendingMachine(CoinRepository coinRepository, List<Item> items) {
        this.coinRepository = coinRepository;
        this.items = items;
    }

    public static VendingMachine from(List<Item> items) {
        PrintView.requestInitialCoin();
        int coin = InputView.requestCoin();
        return new VendingMachine(CoinRepository.of(new LinkedHashMap<>(), coin), items);
    }

    public void showCoinStatus() {
        Map<Integer, Integer> smallChange = coinRepository.getSavedCoin();
        PrintView.printSmallChange(smallChange);
    }

    public void makeOrderList() {
        PrintView.requestItemInformation();
        String itemList = InputView.requestItemList();
        String[] splitItem = itemList.split(";");
        for (String s : splitItem) {
            String[] splitItemInfo = s.substring(1, s.length() - 1).split(",");
            items.add(new Item(splitItemInfo[0], Integer.parseInt(splitItemInfo[1]), Integer.parseInt(splitItemInfo[2])));
        }
    }

    public void requestOrder() {
        PrintView.requestInsertMoney();
        int insertMoney = InputView.requestCoin();
        continueOrder(insertMoney);
    }

    private void continueOrder(int insertMoney) {
        while (true) {
            PrintView.showInsertMoney(insertMoney);
            if (isPossibleMoneyForOrder(insertMoney)) {
                insertMoney = requestItem(insertMoney);
                System.out.println("남은 금액 = " + insertMoney);
                continue;
            }
            break;
        }
        // 남은 금액 출력
        System.out.println("최종 남은 금액 = " + insertMoney);
    }

    private int requestItem(int insertMoney) {
        PrintView.requestItemNameForOrder();
        String itemName = InputView.requestItemForOrder();
        if (isImpossibleItemForOrder(itemName, insertMoney)) {
            return insertMoney;
        }
        return orderThisItem(itemName, insertMoney);
    }

    private int orderThisItem(String itemName, int insertMoney) {
        for (Item item : items) {
            if(item.getItemName().equals(itemName)) {
                item.orderItem();
                insertMoney -= item.getPrice();
                break;
            }
        }
        return insertMoney;
    }

    private boolean isImpossibleItemForOrder(String itemName, int insertMoney) {
        if(!isContain(itemName)) {
            System.out.println("[ERROR] 없는 Item 입니다. 확인 후 입력해주세요.");
            return true;
        }
        if (!isEnoughMoney(itemName, insertMoney)) {
            System.out.println("[ERROR] 금액이 부족합니다. ");
            return true;
        }
        if (!isEnoughQuantity(itemName)) {
            System.out.println("[ERROR] 재고가 부족합니다. ");
            return true;
        }
        return false;
    }

    private boolean isEnoughMoney(String itemName, int insertMoney) {
        for (Item item : items) {
            if (!item.getItemName().equals(itemName)) {
                continue;
            }
            return item.checkPrice(insertMoney);
        }
        return false;
    }

    private boolean isEnoughQuantity(String itemName) {
        for (Item item : items) {
            if (!item.getItemName().equals(itemName)) {
                continue;
            }
            return item.checkQuantity();
        }
        return false;
    }

    private boolean isContain(String itemName) {
        return items.stream()
            .anyMatch(item -> item.getItemName().equals(itemName));
    }

    private boolean isPossibleMoneyForOrder(int insertMoney) {
        return items.stream()
            .anyMatch(item -> item.checkOrderPossible(insertMoney));
    }
}
