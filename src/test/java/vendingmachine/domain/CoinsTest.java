package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoinsTest {

    @Test
    @DisplayName("자판기가 보유하고 있는 금액으로 옳바른 잔돈을 생성하는 지")
    void Should_ReturnCoins_When_CreateCoins() {
        // given
        int money = 1000;

        // when
        Coins coins = new Coins(money);
        int actual = 0;

        Map<Coin, Integer> actualCoin = coins.getCoins();
        for (Coin coin : actualCoin.keySet()) {
            actual += coin.getAmount() * actualCoin.get(coin);
        }

        // then
        assertThat(actual).isEqualTo(money);
    }

}
