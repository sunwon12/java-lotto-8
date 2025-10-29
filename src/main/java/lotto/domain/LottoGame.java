package lotto.domain;

import lotto.Lotto;
import lotto.generator.LottoGenerator;
import lotto.generator.RandomLottoNumberGenerator;

public class LottoGame {
    private final Lottos lottos;
    private final WinningNumbers winningNumbers;
    private final int bonusNumber;

    private LottoGame(Lottos lottos, WinningNumbers winningNumbers, int bonusNumber) {
        this.lottos = lottos;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoGame create(int purchaseAmount, WinningNumbers winningNumbers, int bonusNumber) {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomLottoNumberGenerator());
        Lottos lottos = Lottos.generateLottos(purchaseAmount, lottoGenerator);
        return new LottoGame(lottos, winningNumbers, bonusNumber);
    }

    public Lottos getLottos() {
        return lottos;
    }

    public LottoResult play() {
        LottoResult result = new LottoResult();
        for (Lotto lotto : lottos.getLottos()) {
            LottoRank rank = winningNumbers.match(lotto, bonusNumber);
            result.addResult(rank);
        }
        return result;
    }
}
