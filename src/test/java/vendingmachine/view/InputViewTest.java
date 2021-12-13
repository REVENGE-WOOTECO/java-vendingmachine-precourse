package vendingmachine.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final InputView inputView = new InputView();

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

    @Test
    @DisplayName("알맞은 금액 입력 시 int형으로 반환")
    void isRightInputMoney() {
        // given
        command("300");

        // when
        // then
        assertThatCode(() -> inputView.inputMoney())
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자로 금액 입력 시 Exception 발생")
    void isNotRightInputMoneyByLetter() {
        // given
        command("삼백원");

        // when
        try {
            inputView.inputMoney();
        } catch (final NoSuchElementException ignore) {
        }

        // then
        assertThat(output.toString()).contains("[ERROR] 보유할 금액으로 문자를 입력할 수 없습니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("10원으로 나누어 떨어지지 않는 금액 입력 시 Exception 발생")
    void isNotRightInputMoneyByNotDivide10() {
        // given
        command("355");

        // when
        try {
            inputView.inputMoney();
        } catch (final NoSuchElementException ignore) {
        }

        // then
        assertThat(output.toString()).contains("[ERROR] 보유할 금액이 10원 단위로 입력해야합니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("공백으로 금액 입력 시 Exception 발생")
    void isNotRightInputMoneyByWhiteSpace() {
        // given
        command(" ");

        // when
        try {
            inputView.inputMoney();
        } catch (final NoSuchElementException ignore) {
        }

        // then
        assertThat(output.toString()).contains("[ERROR] 보유할 금액으로 공백을 입력할 수 없습니다. 다시 입력해주세요.");
    }

    @Test
    @DisplayName("공백으로 금액 입력 시 Exception 발생")
    void isNotRightInputMoneyByNegative() {
        // given
        command("-1");

        // when
        try {
            inputView.inputMoney();
        } catch (final NoSuchElementException ignore) {
        }

        // then
        assertThat(output.toString()).contains("[ERROR] 보유할 금액으로 음수를 입력할 수 없습니다. 다시 입력해주세요.");
    }

}
