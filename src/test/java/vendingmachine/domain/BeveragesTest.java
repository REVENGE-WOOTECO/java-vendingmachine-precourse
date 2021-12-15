package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import vendingmachine.dto.BeverageDto;

class BeveragesTest {

    @Test
    @DisplayName("상품들의 이름이 중복되는 경우")
    void should_ThrowException_When_BeverageNameDuplicate() {
        // given
        List<BeverageDto> inputBeverages = new ArrayList<>();
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "20")));
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "20")));

        // when
        // then
        assertThatThrownBy(() -> new Beverages(inputBeverages))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 동일한 상품명은 입력할 수 없습니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("상품들 중 최저 가격을 반환하는 지")
    void should_ReturnMinPrice_When_BeveragesFindMinPrice() {
        // given
        List<BeverageDto> inputBeverages = new ArrayList<>();
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "20")));
        inputBeverages.add(BeverageDto.from(Arrays.array("사이다", "1000", "20")));
        Beverages beverages = new Beverages(inputBeverages);

        // when
        int minPrice = beverages.findMinPrice();

        // then
        assertThat(minPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("상품이름으로 상품 검색이 되는 지")
    void should_ReturnBeverage_When_GivenBeverageName() {
        // given
        List<BeverageDto> inputBeverages = new ArrayList<>();
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "20")));
        inputBeverages.add(BeverageDto.from(Arrays.array("사이다", "1000", "20")));
        Beverages beverages = new Beverages(inputBeverages);

        // when
        Beverage beverage = beverages.findBeverageByName("콜라");

        // then
        assertThat(beverage).isEqualTo(new Beverage("콜라", 1500, 20));
    }

    @Test
    @DisplayName("상품을 구매했을 때 상품 수량이 줄어드는 지")
    void should_SubAmount_When_BuyBeverage() {
        // given
        List<BeverageDto> inputBeverages = new ArrayList<>();
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "1")));
        inputBeverages.add(BeverageDto.from(Arrays.array("사이다", "1000", "20")));
        Beverages beverages = new Beverages(inputBeverages);

        // when
        beverages.BuyBeverage("콜라");

        // then
        assertThatThrownBy(() -> beverages.BuyBeverage("콜라"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 해당 상품은 품절입니다.");
    }

    @Test
    @DisplayName("모든 상품이 품절인 경우")
    void should_True_When_BeveragesIsSoldOut() {
        // given
        List<BeverageDto> inputBeverages = new ArrayList<>();
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "1")));
        inputBeverages.add(BeverageDto.from(Arrays.array("사이다", "1000", "1")));
        Beverages beverages = new Beverages(inputBeverages);

        // when
        beverages.BuyBeverage("콜라");
        beverages.BuyBeverage("사이다");

        // then
        assertThat(beverages.isAllSoled()).isTrue();
    }

    @Test
    @DisplayName("모든 상품이 품절이 아닌 경우")
    void should_False_When_BeveragesIsNotSoldOut() {
        // given
        List<BeverageDto> inputBeverages = new ArrayList<>();
        inputBeverages.add(BeverageDto.from(Arrays.array("콜라", "1500", "1")));
        inputBeverages.add(BeverageDto.from(Arrays.array("사이다", "1000", "20")));
        Beverages beverages = new Beverages(inputBeverages);

        // when
        beverages.BuyBeverage("콜라");

        // then
        assertThat(beverages.isAllSoled()).isFalse();
    }
}
