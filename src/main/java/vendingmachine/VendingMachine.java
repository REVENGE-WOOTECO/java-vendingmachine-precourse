package vendingmachine;

import java.util.LinkedHashMap;
import java.util.Map;

import vendingmachine.model.CoinRepository;
import vendingmachine.view.InputView;
import vendingmachine.view.PrintView;

public class VendingMachine {

    private CoinRepository coinRepository;

    private VendingMachine(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public static VendingMachine from() {
        int coin = InputView.requestCoin();
        return new VendingMachine(CoinRepository.of(new LinkedHashMap<>(), coin));
    }

    public void showCoinStatus() {
        Map<Integer, Integer> smallChange = coinRepository.getSavedCoin();
        PrintView.printSmallChange(smallChange);
    }

    public void makeOrderList() {
        String orderList = InputView.requestItemList();
    }
}
