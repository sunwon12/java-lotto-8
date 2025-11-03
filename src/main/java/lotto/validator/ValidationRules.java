package lotto.validator;

import lotto.constant.LottoConstants;

import java.util.List;

public class ValidationRules {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_BONUS_NUMBER_RANGE = ERROR_PREFIX + "보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_DUPLICATE = ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";

    public static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < LottoConstants.LOTTO_RANGE_MIN || bonusNumber > LottoConstants.LOTTO_RANGE_MAX) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_RANGE);
        }
    }

    public static void validateBonusNumberDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}
