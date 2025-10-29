package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.constant.LottoConstants;

import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(
                LottoConstants.LOTTO_RANGE_MIN,
                LottoConstants.LOTTO_RANGE_MAX,
                LottoConstants.LOTTO_NUMBER_COUNT
        );
    }
}
