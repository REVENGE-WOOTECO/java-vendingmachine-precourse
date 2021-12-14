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

    @Test
    @DisplayName("코인의 amount 값으로 코인을 반환하는 지")
    void Should_ReturnCoin_When_FindByAmount() {
        // given
        int amount = 100;

        // when
        Coin coin = Coin.findByAmount(amount);

        // then
        assertThat(coin).isEqualTo(Coin.COIN_100);
    }
}
