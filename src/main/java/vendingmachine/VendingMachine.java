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
    private int smallChange;

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
        smallChange = InputView.requestCoin();
        continuousOrder();
    }

    private void continuousOrder() {
        while (true) {
            PrintView.showInsertMoney(smallChange);
            if (isPossibleMoneyForOrder()) {
                smallChange = requestItem();
                continue;
            }
            break;
        }
        showSmallChange();
    }

    private void showSmallChange() {
        // TODO 구현 : 잔돈 주고, 나머지는 자판기가 먹도록 구현
        System.out.println("smallChange = " + smallChange);
    }

    private int requestItem() {
        PrintView.requestItemNameForOrder();
        String itemName = InputView.requestItemForOrder();
        if (isImpossibleItemForOrder(itemName)) {
            return smallChange;
        }
        return orderThisItem(itemName);
    }

    private int orderThisItem(String itemName) {
        for (Item item : items) {
            if(item.getItemName().equals(itemName)) {
                item.orderItem();
                smallChange -= item.getPrice();
                break;
            }
        }
        return smallChange;
    }

    private boolean isImpossibleItemForOrder(String itemName) {
        if(!isContain(itemName)) {
            System.out.println("[ERROR] 없는 Item 입니다. 확인 후 입력해주세요.");
            return true;
        }
        if (!isEnoughMoney(itemName)) {
            System.out.println("[ERROR] 금액이 부족합니다. ");
            return true;
        }
        if (!isEnoughQuantity(itemName)) {
            System.out.println("[ERROR] 재고가 부족합니다. ");
            return true;
        }
        return false;
    }

    private boolean isEnoughMoney(String itemName) {
        for (Item item : items) {
            if (!item.getItemName().equals(itemName)) {
                continue;
            }
            return item.checkPrice(smallChange);
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

    private boolean isPossibleMoneyForOrder() {
        return items.stream()
            .anyMatch(item -> item.checkOrderPossible(smallChange));
    }
}
