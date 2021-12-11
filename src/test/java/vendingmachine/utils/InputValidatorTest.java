package vendingmachine.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("InputValidator Unit Test")
class InputValidatorTest {

    @ParameterizedTest
    @DisplayName("금액 입력 실패 테스트")
    @CsvSource({"abc", "def", "-100", "1004", "1023034"})
    void is_not_valid_money_input(String input) {
        assertThat(InputValidator.isNotValidInputMoney(input)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("아이템 입력 실패 테스트")
    @ValueSource(strings = {"[100,콜라,20];[사이다,adf,10]", "[콜라,10,20];[사이다,20,abc]", "(콜라,100,20);(사이다,200,20)", "[콜라,134,2];[사이다,222,10]"})
    void is_not_valid_item_input(String input) {
        assertThat(InputValidator.isNotValidItemList(input.split(";"))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("아이템 중복 실패 테스트")
    @ValueSource(strings = {"[콜라,100,2];[콜라,200,10]", "[사이다,100,20];[콜라,300,20];[사이다,100,10]"})
    void is_duplicated_item_name_input(String input) {
        assertThat(InputValidator.isNotValidItemList(input.split(";"))).isTrue();
    }
}