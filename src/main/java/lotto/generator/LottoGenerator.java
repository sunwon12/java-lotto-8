package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.constant.LottoConstants;

import java.util.List;

public class LottoGenerator {

    public static int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LottoConstants.PRICE_PER_TICKET;
    }

    public static Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LottoConstants.LOTTO_RANGE_MIN,
                LottoConstants.LOTTO_RANGE_MAX,
                LottoConstants.LOTTO_NUMBER_COUNT
        );
        return new Lotto(numbers);
    }
}
