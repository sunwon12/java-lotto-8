package lotto.domain;

import lotto.generator.FixedLottoNumberGenerator;
import lotto.generator.LottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {

    @Test
    void 로또_게임을_생성하고_결과를_계산한다() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoGame game = LottoGame.create(8000, winningNumbers);

        assertThat(game.getLottos().getCount()).isEqualTo(8);
    }

    @Test
    void 로또_게임의_결과를_계산한다() {
        FixedLottoNumberGenerator fixedGenerator = new FixedLottoNumberGenerator(
                List.of(
                        List.of(1, 2, 3, 4, 5, 6),  // 1등
                        List.of(1, 2, 3, 4, 5, 7),  // 2등 (보너스 7 일치)
                        List.of(1, 2, 3, 4, 5, 8)   // 3등
                )
        );
        LottoGenerator lottoGenerator = new LottoGenerator(fixedGenerator);
        Lottos lottos = Lottos.generateLottos(3000, lottoGenerator);

        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.calculateResult(winningNumbers);

        assertThat(result.getCount(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.getCount(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.getCount(LottoRank.THIRD)).isEqualTo(1);
    }
}
