package lotto.domain;

import lotto.validator.ValidationRules;

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
        ValidationRules.validateBonusNumberRange(bonusNumber);
        ValidationRules.validateBonusNumberDuplicate(bonusNumber, numbers);
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
