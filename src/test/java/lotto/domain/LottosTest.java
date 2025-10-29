package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    void 구입_금액에_따라_로또가_발행된다() {
        Lottos lottos = Lottos.generateLottos(8000);
        assertThat(lottos.getCount()).isEqualTo(8);
    }

    @Test
    void 발행된_모든_로또는_6개의_번호를_가진다() {
        Lottos lottos = Lottos.generateLottos(3000);
        assertThat(lottos.getLottos()).allMatch(lotto -> lotto.getNumbers().size() == 6);
    }

    @Test
    void 발행된_모든_로또_번호는_1부터_45_사이의_값이다() {
        Lottos lottos = Lottos.generateLottos(3000);
        assertThat(lottos.getLottos()).allMatch(lotto ->
                lotto.getNumbers().stream().allMatch(number -> number >= 1 && number <= 45)
        );
    }
}
