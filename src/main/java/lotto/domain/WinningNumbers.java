package lotto.domain;

import lotto.Lotto;
import lotto.constant.LottoConstants;

import java.util.List;

public class WinningNumbers {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.lotto = new Lotto(numbers);
        validateBonusNumber(bonusNumber, numbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber, List<Integer> numbers) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(bonusNumber, numbers);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < LottoConstants.LOTTO_RANGE_MIN || bonusNumber > LottoConstants.LOTTO_RANGE_MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateBonusNumberDuplicate(int bonusNumber, List<Integer> numbers) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return lotto.getNumbers();
    }

    public LottoRank match(Lotto lotto) {
        int matchCount = countMatchingNumbers(lotto);
        boolean bonusMatch = checkBonusMatch(lotto);
        return LottoRank.valueOf(matchCount, bonusMatch);
    }

    private int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(this.lotto.getNumbers()::contains)
                .count();
    }

    private boolean checkBonusMatch(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
