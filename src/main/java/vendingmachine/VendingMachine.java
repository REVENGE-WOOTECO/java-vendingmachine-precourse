package vendingmachine;

import java.util.Arrays;
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
        int coin = InputView.requestCoin();
        return new VendingMachine(CoinRepository.of(new LinkedHashMap<>(), coin), items);
    }

    public void showCoinStatus() {
        Map<Integer, Integer> smallChange = coinRepository.getSavedCoin();
        PrintView.printSmallChange(smallChange);
    }

    public void makeOrderList() {
        String itemList = InputView.requestItemList();
        String[] splitItem = itemList.split(";");
        for (String s : splitItem) {
            String[] splitItemInfo = s.substring(1, s.length() - 1).split(",");
            items.add(new Item(splitItemInfo[0], Integer.parseInt(splitItemInfo[1]), Integer.parseInt(splitItemInfo[2])));
        }
    }
}
