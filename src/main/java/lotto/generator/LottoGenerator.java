package lotto.generator;

import lotto.domain.Lotto;
import lotto.constant.LottoConstants;

import java.util.List;

public class LottoGenerator {
    private final LottoNumberGenerator numberGenerator;

    public LottoGenerator(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public static int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LottoConstants.PRICE_PER_TICKET;
    }

    public Lotto generateLotto() {
        List<Integer> numbers = numberGenerator.generate();
        return new Lotto(numbers);
    }
}
