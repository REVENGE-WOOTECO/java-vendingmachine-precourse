package vendingmachine.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("InputValidator Unit Test")
class InputValidatorTest {

    @ParameterizedTest
    @DisplayName("금액 입력 실패 테스트")
    @CsvSource({"abc", "def", "-100", "1004", "1023034"})
    void is_not_valid_money_input(String input) {
        assertThat(InputValidator.isNotValidInputMoney(input)).isTrue();
    }

}