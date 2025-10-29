package lotto.domain;

import lotto.generator.LottoGenerator;
import lotto.generator.RandomLottoNumberGenerator;

public class LottoGame {
    private final Lottos lottos;
    private final WinningNumbers winningNumbers;

    private LottoGame(Lottos lottos, WinningNumbers winningNumbers) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
    }

    public static LottoGame create(int purchaseAmount, WinningNumbers winningNumbers) {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lottos lottos = Lottos.generateLottos(purchaseAmount, lottoGenerator);
        return new LottoGame(lottos, winningNumbers);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public LottoResult play() {
        return lottos.calculateResult(winningNumbers);
    }
}
