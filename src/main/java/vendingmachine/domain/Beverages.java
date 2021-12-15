package vendingmachine.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Beverages {
    public static final String ERROR_DUPLICATE_BEVERAGE = "[ERROR] 동일한 상품명은 입력할 수 없습니다. 다시 입력해주세요.";
    public static final String ERROR_NOT_FIND_MIN_PRICE = "[ERROR] 최저 가격을 찾을 수 없습니다.";
    public static final String ERROR_NOT_FIND_BEVERAGE = "[ERROR] 상품을 찾을 수 없습니다.";
    public static final String COMMA_DETERMINE = ",";
    public static final int SUBSTRING_IDX = 1;
    public static final int NAME_IDX = 0;
    public static final int PRICE_IDX = 1;
    public static final int AMOUNT_IDX = 2;

    private final List<Beverage> beverages;

    public Beverages(String[] inputBeverages) {
        this.beverages = createBeverages(inputBeverages);
    }

    public List<Beverage> createBeverages(String[] inputBeverages) {
        List<Beverage> beverages = new ArrayList<>();

        for (String inputBeverage : inputBeverages) {
            String[] beverageInfos = splitInfos(inputBeverage);
            String name = beverageInfos[NAME_IDX];
            int price = Integer.parseInt(beverageInfos[PRICE_IDX]);
            int amount = Integer.parseInt(beverageInfos[AMOUNT_IDX]);
            beverages.add(new Beverage(name, price, amount));
        }

        validateDuplicate(beverages);
        return beverages;
    }

    private String[] splitInfos(String beverage) {
        String beverageInfo = beverage.substring(SUBSTRING_IDX, beverage.length() - SUBSTRING_IDX);
        return beverageInfo.split(COMMA_DETERMINE);
    }

    private void validateDuplicate(List<Beverage> beverages) {
        Set<Beverage> nonDuplicateBeverages = new HashSet<>(beverages);
        if (nonDuplicateBeverages.size() != beverages.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BEVERAGE);
        }
    }

    public int findMinPrice() {
        return beverages.stream()
            .map(Beverage::getPrice)
            .min(Integer::compare)
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_FIND_MIN_PRICE));
    }

    public void BuyBeverage(String name) {
        Beverage beverage = findBeverageByName(name);
        beverage.BuyBeverage();
    }

    public boolean isAllSoled() {
        return beverages.stream()
            .allMatch(Beverage::isSoldOut);
    }

    public Beverage findBeverageByName(String name) {
        return beverages.stream()
            .filter(beverage -> beverage.isSameName(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_FIND_BEVERAGE));
    }
}
