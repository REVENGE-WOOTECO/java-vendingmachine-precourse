package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinTest {

    @Test
    @DisplayName("코인의 amount 리스트를 반환하는 지")
    void Should_ReturnCoinAmountList_When_GetCoinAmountList() {
        // give
        // when
        // then
        assertThat(Coin.getCoinAmountList().size()).isEqualTo(4);
    }
}
