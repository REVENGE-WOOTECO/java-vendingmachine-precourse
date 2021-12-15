package vendingmachine.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import vendingmachine.dto.BeverageDto;
import vendingmachine.utils.validator.ValidateBeverage;
import vendingmachine.utils.validator.ValidateMoney;

class InputViewTest {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
        output.reset();
    }

    private void command(final String... args) {
        final byte[] buf = String.join("\n", args).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @Nested
    @DisplayName("자판기가 보유한 금액 입력")
    public class InputVendingMachineMoney {

        @Test
        @DisplayName("알맞은 금액 입력 시 int형으로 반환")
        void isRightInputMoney() {
            // given
            command("300");

            // when
            int actual = InputView.inputVendingMachineMoney();
            // then
            assertThat(actual).isEqualTo(300);
        }

        @Test
        @DisplayName("문자로 금액 입력 시 Exception 발생")
        void isNotRightInputMoneyByLetter() {
            // given
            command("삼백원");

            // when
            try {
                InputView.inputVendingMachineMoney();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateMoney.ERROR_MONEY_NOT_DIGIT);
        }

        @Test
        @DisplayName("10원으로 나누어 떨어지지 않는 금액 입력 시 Exception 발생")
        void isNotRightInputMoneyByNotDivide10() {
            // given
            command("355");

            // when
            try {
                InputView.inputVendingMachineMoney();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateMoney.ERROR_MONEY_UNIT);
        }

        @Test
        @DisplayName("음수인 금액 입력 시 Exception 발생")
        void isNotRightInputMoneyByNegative() {
            // given
            command("-1");

            // when
            try {
                InputView.inputVendingMachineMoney();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateMoney.ERROR_MONEY_NEGATIVE);
        }

    }

    @Nested
    @DisplayName("상품 입력")
    public class InputBeverages {

        @Test
        @DisplayName("알맞은 상품 입력 시 String[]형으로 반환")
        void Should_ReturnStrings_When_RightInput() {
            // given
            command("[콜라,1500,20];[사이다,1000,10]");

            // when
            List<BeverageDto> actual = InputView.inputBeverages();

            // then
            assertThat(actual.size()).isEqualTo(2);
        }

        @Test
        @DisplayName("알맞지 않는 상품 입력 시 Exception 발생")
        void Should_ThrowException_When_NotRightInput() {
            // given
            command("[콜라,천오백,20];[사이다,1000,이]");

            // when
            try {
                InputView.inputBeverages();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateBeverage.ERROR_BEVERAGE_INPUT);
        }

        @Test
        @DisplayName("상품 가격이 10으로 나눠지지 않을 시 Exception 발생")
        void Should_ThrowException_When_PriceIsNotDivideTen() {
            // given
            command("[콜라,155,20]");

            // when
            try {
                InputView.inputBeverages();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateBeverage.ERROR_PRICE_UNIT);
        }

        @Test
        @DisplayName("상품 가격이 100 미만일 경우 Exception 발생")
        void Should_ThrowException_When_PriceIsNotOver100() {
            // given
            command("[콜라,000,20]");

            // when
            try {
                InputView.inputBeverages();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateBeverage.ERROR_PRICE_RANGE);
        }

        @Test
        @DisplayName("상품 수량이 1 미만일 경우 Exception 발생")
        void Should_ThrowException_When_AmountIsNotOverOne() {
            // given
            command("[콜라,100,0]");

            // when
            try {
                InputView.inputBeverages();
            } catch (final NoSuchElementException ignore) {
            }

            // then
            assertThat(output.toString()).contains(ValidateBeverage.ERROR_AMOUNT_RANGE);
        }

    }
}
