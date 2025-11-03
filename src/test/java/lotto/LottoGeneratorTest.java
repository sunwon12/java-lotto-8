package lotto;

import lotto.domain.Lotto;
import lotto.generator.FixedLottoNumberGenerator;
import lotto.generator.LottoGenerator;
import lotto.generator.RandomLottoNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    void 구입_금액에_따라_로또_개수를_계산한다() {
        assertThat(LottoGenerator.calculateLottoCount(8000)).isEqualTo(8);
        assertThat(LottoGenerator.calculateLottoCount(1000)).isEqualTo(1);
        assertThat(LottoGenerator.calculateLottoCount(5000)).isEqualTo(5);
    }

    @Test
    void 로또_번호_6개가_자동으로_생성된다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lotto lotto = lottoGenerator.generateLotto();
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 생성된_로또_번호는_1부터_45_사이의_값이다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lotto lotto = lottoGenerator.generateLotto();
        assertThat(lotto.getNumbers()).allMatch(number -> number >= 1 && number <= 45);
    }

    @Test
    void 생성된_로또_번호는_중복되지_않는다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lotto lotto = lottoGenerator.generateLotto();
        assertThat(lotto.getNumbers()).doesNotHaveDuplicates();
    }

    @Test
    void 생성된_로또_번호는_오름차순으로_정렬된다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lotto lotto = lottoGenerator.generateLotto();
        assertThat(lotto.getNumbers()).isSorted();
    }

    @Test
    void 고정된_번호로_로또를_생성할_수_있다() {
        FixedLottoNumberGenerator fixedGenerator = new FixedLottoNumberGenerator(
                List.of(List.of(1, 2, 3, 4, 5, 6))
        );
        LottoGenerator lottoGenerator = new LottoGenerator(fixedGenerator);
        Lotto lotto = lottoGenerator.generateLotto();
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }
}
