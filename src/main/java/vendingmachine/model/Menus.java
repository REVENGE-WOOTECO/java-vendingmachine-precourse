package vendingmachine.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Menus {
    private Set<Menu> menus = new HashSet<>();

    public void addMenu(Menu menu) {
        if (menus.stream().anyMatch(menuEntry -> menuEntry.getName().equals(menu.getName()))) {
            throw new IllegalArgumentException("[ERROR] 동일한 이름의 메뉴가 포함되어 있습니다");
        }
        menus.add(menu);
    }

    public void buyMenu(String menuName) {
        Menu menu = menus.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
        int menuCount = menu.decreaseMenuCount();
        if (menuCount == 0) {
            menus.remove(menu);
        }
    }
    public boolean isTerminate(HoldingCoins holdingCoins) {
        if (isAllMenuSoldOut()) {
            return true;
        }
        return isNotPossibleBuyCheapestMenu(holdingCoins);
    }

    private boolean isAllMenuSoldOut() {
        return menus.isEmpty();
    }

    private boolean isNotPossibleBuyCheapestMenu(HoldingCoins holdingCoins) {
        return holdingCoins.getSumOfCoins() < menus.stream()
                .min(Comparator.comparing(Menu::getPrice))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메뉴가 다 팔렸습니다."))
                .getPrice();
    }
}
