package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("돈 차감이 되는 지")
    void should_DeductMoney_When_MoneyIsDeduct() {
        // given
        Money money = new Money(1000);
        // when
        Money actual = money.deduct(500);
        // then
        assertThat(actual.getValue()).isEqualTo(500);
    }

    @Test
    @DisplayName("투입 금액으로 상품을 살 수 있는 지")
    void should_True_When_MoneyIsOverThanPrice() {
        // given
        Money money = new Money(1000);
        int price = 500;

        // when
        boolean actual = money.canBuyBeverage(price);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("투입 금액으로 상품을 살 수 있는 지")
    void should_False_When_MoneyIsNotOverThanPrice() {
        // given
        Money money = new Money(1000);
        int price = 1500;

        // when

        // then
        assertThatThrownBy(() -> money.canBuyBeverage(price))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 돈이 부족합니다.");
    }

}
