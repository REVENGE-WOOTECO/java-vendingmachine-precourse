package vendingmachine.model;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class VendingMachineMenu {
    private static final Pattern menuPattern = Pattern.compile("^\\[[ㄱ-ㅎ가-힣a-zA-Z]+,[\\d]+,[\\d]+]$");

    private final Set<Menu> menus = new HashSet<>();

    public VendingMachineMenu(String inputMenu) {
        String[] menuArray = inputMenu.split(";");
        for (String menu : menuArray) {
            isValidPattern(menu);
            String[] menuInfo = deleteBrackets(menu).split(",");
            menus.add(new Menu(menuInfo[0], menuInfo[1], menuInfo[2]));
        }
    }

    private void isValidPattern(String menu) {
        if (!menuPattern.matcher(menu).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바른 형식으로 메뉴를 입력해주세요");
        }
    }

    private String deleteBrackets(String menu) {
        return menu.substring(1, menu.length() - 2);
    }

    public Set<Menu> getMenus() {
        return new HashSet<>(this.menus);
    }
}
