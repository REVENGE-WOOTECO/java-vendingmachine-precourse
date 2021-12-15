package vendingmachine.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
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

    @Test
    @DisplayName("남은 금액을 통해 옳바른 잔돈을 반환하는 지")
    void Should_ReturnCoins_When_ReturnCoins() {
        assertRandomNumberInListTest(
            () -> {
                Coins coins = new Coins(900);
                Map<Coin, Integer> actual = coins.calculateReturnCoins(760);
                assertThat(actual.size()).isEqualTo(4);
                assertThat(actual.get(Coin.COIN_500)).isEqualTo(1);
                assertThat(actual.get(Coin.COIN_100)).isEqualTo(2);
                assertThat(actual.get(Coin.COIN_50)).isEqualTo(1);
                assertThat(actual.get(Coin.COIN_10)).isEqualTo(1);
            },
            10, 500, 100, 100, 100, 50, 10, 10, 10, 10, 10
        );
    }

}
