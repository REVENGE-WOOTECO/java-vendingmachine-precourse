package vendingmachine.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Menus {
    private final Set<Menu> menus;

    public Menus(Set<Menu> menus) {
        isValidMenus(menus);
        this.menus = new HashSet<>(menus);
    }

    private void isValidMenus(Set<Menu> menus) {
        Set<String> menuNameSet = menus.stream().map(Menu::getName).collect(Collectors.toSet());
        if (menus.size() != menuNameSet.size()) {
            throw new IllegalArgumentException("[ERROR] 동일한 이름의 메뉴가 포함되어 있습니다");
        }
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
    public boolean isTerminate(InsertedMoney insertedMoney) {
        if (isAllMenuSoldOut()) {
            return true;
        }
        return isNotPossibleBuyCheapestMenu(insertedMoney);
    }

    private boolean isAllMenuSoldOut() {
        return menus.isEmpty();
    }

    private boolean isNotPossibleBuyCheapestMenu(InsertedMoney insertedMoney) {
        return insertedMoney.getInsertedMoney() < menus.stream()
                .min(Comparator.comparing(Menu::getPrice))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 메뉴가 다 팔렸습니다."))
                .getPrice();
    }
}
