package lotto.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusNumberValidatorTest {

    @Test
    void 정상적인_보너스_번호_입력_시_파싱된_숫자를_반환한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int result = InputValidator.validateAndParseBonusNumber("7", winningNumbers);
        assertThat(result).isEqualTo(7);
    }

    @Test
    void 공백이_포함된_보너스_번호_입력_시_정상_처리된다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        int result = InputValidator.validateAndParseBonusNumber(" 7 ", winningNumbers);
        assertThat(result).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "46", "100", "-1"})
    void 보너스_번호가_범위를_벗어나면_예외가_발생한다(String input) {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateAndParseBonusNumber(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("1부터 45");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "일곱", "7번", " ", ""})
    void 보너스_번호가_숫자가_아니면_예외가_발생한다(String input) {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateAndParseBonusNumber(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateAndParseBonusNumber("6", winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("중복");
    }

    @Test
    void 보너스_번호가_당첨_번호_중_하나와_같으면_예외가_발생한다() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> InputValidator.validateAndParseBonusNumber("3", winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("중복");
    }
}
