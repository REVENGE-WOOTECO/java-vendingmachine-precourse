package vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import vendingmachine.model.Coin;
import vendingmachine.model.CoinRepository;
import vendingmachine.view.InputView;

public class VendingMachine {

    private CoinRepository coinRepository;

    private VendingMachine(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public static VendingMachine from() {
        int coin = InputView.requestCoin();
        return new VendingMachine(CoinRepository.of(new LinkedHashMap<>(), coin));
    }
}
