package lotto.domain;

import lotto.generator.FixedLottoNumberGenerator;
import lotto.generator.LottoGenerator;
import lotto.generator.RandomLottoNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    void 구입_금액에_따라_로또가_발행된다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lottos lottos = Lottos.generateLottos(8000, lottoGenerator);
        assertThat(lottos.getCount()).isEqualTo(8);
    }

    @Test
    void 발행된_모든_로또는_6개의_번호를_가진다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lottos lottos = Lottos.generateLottos(3000, lottoGenerator);
        assertThat(lottos.getLottos()).allMatch(lotto -> lotto.getNumbers().size() == 6);
    }

    @Test
    void 발행된_모든_로또_번호는_1부터_45_사이의_값이다() {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lottos lottos = Lottos.generateLottos(3000, lottoGenerator);
        assertThat(lottos.getLottos()).allMatch(lotto ->
                lotto.getNumbers().stream().allMatch(number -> number >= 1 && number <= 45)
        );
    }

    @Test
    void 고정된_번호로_여러_로또를_생성할_수_있다() {
        FixedLottoNumberGenerator fixedGenerator = new FixedLottoNumberGenerator(
                List.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(7, 8, 9, 10, 11, 12)
                )
        );
        LottoGenerator lottoGenerator = new LottoGenerator(fixedGenerator);
        Lottos lottos = Lottos.generateLottos(2000, lottoGenerator);

        assertThat(lottos.getCount()).isEqualTo(2);
        assertThat(lottos.getLottos().get(0).getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        assertThat(lottos.getLottos().get(1).getNumbers()).containsExactly(7, 8, 9, 10, 11, 12);
    }
}
