package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {

    @Test
    void 정상적인_당첨_번호로_객체를_생성한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 당첨_번호는_자동으로_오름차순_정렬된다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(6, 5, 4, 3, 2, 1));
        assertThat(winningNumbers.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 당첨_번호가_6개가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 당첨_번호에_중복이_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("중복");
    }

    @Test
    void 당첨_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]")
                .hasMessageContaining("1부터 45");
    }
}
