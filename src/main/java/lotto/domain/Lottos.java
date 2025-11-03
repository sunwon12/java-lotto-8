package lotto.domain;

import lotto.generator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generateLottos(int purchaseAmount, LottoGenerator lottoGenerator) {
        int lottoCount = LottoGenerator.calculateLottoCount(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(lottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getCount() {
        return lottos.size();
    }

    public LottoResult calculateResult(WinningNumbers winningNumbers) {
        LottoResult result = new LottoResult();
        lottos.stream()
                .map(winningNumbers::match)
                .forEach(result::addResult);
        return result;
    }
}
