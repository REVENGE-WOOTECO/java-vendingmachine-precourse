package vendingmachine;

import static vendingmachine.utils.Constant.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vendingmachine.model.CoinRepository;
import vendingmachine.model.Item;
import vendingmachine.view.InputView;
import vendingmachine.view.PrintView;

public class VendingMachine {
    private final CoinRepository coinRepository;
    private final List<Item> items;
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
        PrintView.printCoinWithZeroAmount();
        PrintView.printSmallChange(smallChange);
    }

    public void makeOrderList() {
        PrintView.requestItemInformation();
        String itemList = InputView.requestItemList();
        String[] splitItem = itemList.split(ITEM_SPLIT_VALUE);
        for (String s : splitItem) {
            String[] splitItemInfo = s.substring(1, s.length() - 1).split(INFO_SPLIT_VALUE);
            items.add(new Item(splitItemInfo[NAME_INDEX], Integer.parseInt(splitItemInfo[PRICE_INDEX]), Integer.parseInt(splitItemInfo[QUANTITY_INDEX])));
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
                requestItem();
                continue;
            }
            break;
        }
        showResult();
    }

    private boolean isPossibleMoneyForOrder() {
        return items.stream()
            .anyMatch(item -> item.checkOrderPossible(smallChange));
    }

    private void requestItem() {
        PrintView.requestItemNameForOrder();
        String itemName = InputView.requestItemForOrder();
        if (isImpossibleItemForOrder(itemName)) {
            return;
        }
        orderThisItem(itemName);
    }

    private void showResult() {
        Map<Integer, Integer> smallChangeMap = coinRepository.showSmallChange(smallChange);
        PrintView.printResultMoreThanZero();
        PrintView.printSmallChange(smallChangeMap);
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

    private void orderThisItem(String itemName) {
        for (Item item : items) {
            if(item.getItemName().equals(itemName)) {
                item.orderItem();
                smallChange -= item.getPrice();
                return;
            }
        }
    }

    private boolean isContain(String itemName) {
        return items.stream()
            .anyMatch(item -> item.getItemName().equals(itemName));
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
}
