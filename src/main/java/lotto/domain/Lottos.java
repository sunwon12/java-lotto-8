package lotto.domain;

import lotto.Lotto;
import lotto.generator.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generateLottos(int purchaseAmount) {
        int lottoCount = LottoGenerator.calculateLottoCount(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getCount() {
        return lottos.size();
    }
}
