package lotto.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "8000", "10000"})
    void 정상적인_구입_금액_입력_시_파싱된_금액을_반환한다(String input) {
        int result = InputValidator.validateAndParsePurchaseAmount(input);
        assertThat(result).isEqualTo(Integer.parseInt(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1500", "2300", "999", "10001"})
    void 천원_단위가_아닌_금액_입력_시_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateAndParsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("1,000원 단위");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000", "-5000"})
    void 영_또는_음수_금액_입력_시_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateAndParsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("양수");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "천원", "1000원", " ", ""})
    void 숫자가_아닌_문자_입력_시_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateAndParsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("숫자");
    }

    @Test
    void 공백이_포함된_정상_금액_입력_시_정상_처리된다() {
        int result = InputValidator.validateAndParsePurchaseAmount(" 5000 ");
        assertThat(result).isEqualTo(5000);
    }
}
