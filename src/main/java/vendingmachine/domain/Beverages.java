package vendingmachine.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import vendingmachine.dto.BeverageDto;

public class Beverages {
    private static final String ERROR_DUPLICATE_BEVERAGE = "[ERROR] 동일한 상품명은 입력할 수 없습니다. 다시 입력해주세요.";
    private static final String ERROR_NOT_FIND_MIN_PRICE = "[ERROR] 최저 가격을 찾을 수 없습니다.";
    private static final String ERROR_NOT_FIND_BEVERAGE = "[ERROR] 상품을 찾을 수 없습니다.";

    private final List<Beverage> beverages;

    public Beverages(List<BeverageDto> inputBeverages) {
        List<Beverage> beverages = inputBeverages.stream()
            .map(BeverageDto::toEntity)
            .collect(Collectors.toList());
        validateDuplicate(beverages);
        this.beverages = beverages;
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
