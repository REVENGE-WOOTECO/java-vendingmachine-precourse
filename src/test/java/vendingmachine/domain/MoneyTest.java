package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100})
    @DisplayName("돈이 음수가 되는 경우")
    void should_ThrowException_When_MoneyIsNegative(int money) {
        assertThatThrownBy(() -> new Money(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 보유할 금액으로 음수를 입력할 수 없습니다. 다시 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 15, 155})
    @DisplayName("돈이 10으로 나눠지지 않는 경우")
    void should_ThrowException_When_MoneyIsNotDivideTen(int money) {
        assertThatThrownBy(() -> new Money(money))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 보유할 금액이 10원 단위로 입력해야합니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("돈 차감이 되는 지")
    void should_DeductMoney_When_MoneyIsDeduct() {
        // given
        Money money = new Money(1000);
        // when
        Money actual = money.deduct(new Money(500));
        // then
        assertThat(actual.getMoney()).isEqualTo(500);
    }

}
