package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BeveragesTest {

    @Test
    @DisplayName("상품들의 이름이 중복되는 경우")
    void should_ThrowException_When_BeverageNameDuplicate() {
        // given
        String[] inputBeverages = "[콜라,1500,20];[콜라,1000,10]".split(";");

        // when
        // then
        assertThatThrownBy(() -> new Beverages(inputBeverages))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 동일한 상품명은 입력할 수 없습니다. 다시 입력해주세요.");
    }

}
