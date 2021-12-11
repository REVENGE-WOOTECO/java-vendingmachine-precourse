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
                continue;
            }
            break;
        }
        // 남은 금액 출력
    }

    // 상품 입력받고 주문하기, 주문할 수 없는 입력은 경고주고 다시 입력 받기
    private int requestItem(int insertMoney) {
        PrintView.requestItemNameForOrder();
        String itemName = InputView.requestItemForOrder();

        return insertMoney;
    }

    private boolean isPossibleMoneyForOrder(int insertMoney) {
        return items.stream()
            .anyMatch(item -> item.checkOrderPossible(insertMoney));
    }
}
