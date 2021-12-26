package vendingmachine;

import static vendingmachine.utils.Constant.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vendingmachine.model.CoinRepository;
import vendingmachine.model.Item;
import vendingmachine.model.ItemRepository;
import vendingmachine.view.InputView;
import vendingmachine.view.PrintView;

public class VendingMachine {
    private final CoinRepository coinRepository;
    private final ItemRepository itemRepository;
    private int smallChange;

    private VendingMachine(CoinRepository coinRepository, ItemRepository itemRepository) {
        this.coinRepository = coinRepository;
        this.itemRepository = itemRepository;
    }

    public static VendingMachine from(List<Item> items) {
        PrintView.requestInitialCoin();
        int coin = InputView.requestCoin();
        return new VendingMachine(CoinRepository.of(new LinkedHashMap<>(), coin), ItemRepository.of(items));
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
            itemRepository.addItem(new Item(splitItemInfo[NAME_INDEX], Integer.parseInt(splitItemInfo[PRICE_INDEX]), Integer.parseInt(splitItemInfo[QUANTITY_INDEX])));
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
        return itemRepository.checkOrderAble(smallChange);
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
        Map<Integer, Integer> smallChangeMap = coinRepository.getSmallChange(smallChange);
        PrintView.printResultMoreThanZero();
        PrintView.printSmallChange(smallChangeMap);
    }

    private boolean isImpossibleItemForOrder(String itemName) {
        if(!isContain(itemName)) {
            PrintView.printNoContain();
            return true;
        }
        if (!isEnoughMoney(itemName)) {
            PrintView.printNoEnoughMoney();
            return true;
        }
        if (!isEnoughQuantity(itemName)) {
            PrintView.printNoEnoughQuantity();
            return true;
        }
        return false;
    }

    private void orderThisItem(String itemName) {
        for (Item item : itemRepository.getItems()) {
            if(item.getItemName().equals(itemName)) {
                item.orderItem();
                smallChange -= item.getPrice();
                return;
            }
        }
    }

    private boolean isContain(String itemName) {
        return itemRepository.checkContainThisItem(itemName);
    }

    private boolean isEnoughMoney(String itemName) {
        return itemRepository.checkEnoughMoney(itemName, smallChange);
    }

    private boolean isEnoughQuantity(String itemName) {
        return itemRepository.checkEnoughQuantity(itemName);
    }
}
