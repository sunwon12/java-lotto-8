package lotto.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersValidatorTest {

    @Test
    void 정상적인_당첨_번호_입력_시_파싱된_리스트를_반환한다() {
        List<Integer> result = InputValidator.validateAndParseWinningNumbers("1,2,3,4,5,6");
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 공백이_포함된_당첨_번호_입력_시_정상_처리된다() {
        List<Integer> result = InputValidator.validateAndParseWinningNumbers(" 1 , 2 , 3 , 4 , 5 , 6 ");
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7", "1,2,3", "1"})
    void 당첨_번호가_6개가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateAndParseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("6개");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,c,d,e,f", "1,2,3,4,5,a", "1,2,3,4,5,"})
    void 당첨_번호가_숫자가_아니면_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateAndParseWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 빈_문자열_입력_시_예외가_발생한다() {
        assertThatThrownBy(() -> InputValidator.validateAndParseWinningNumbers(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
