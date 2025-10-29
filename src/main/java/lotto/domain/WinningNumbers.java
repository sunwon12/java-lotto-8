package lotto.domain;

import lotto.Lotto;

import java.util.List;

public class WinningNumbers {
    private final Lotto lotto;

    public WinningNumbers(List<Integer> numbers) {
        this.lotto = new Lotto(numbers);
    }

    public List<Integer> getNumbers() {
        return lotto.getNumbers();
    }

    public LottoRank match(Lotto lotto, int bonusNumber) {
        int matchCount = countMatchingNumbers(lotto);
        boolean bonusMatch = checkBonusMatch(lotto, bonusNumber);
        return LottoRank.valueOf(matchCount, bonusMatch);
    }

    private int countMatchingNumbers(Lotto lotto) {
        return (int) lotto.getNumbers().stream()
                .filter(this.lotto.getNumbers()::contains)
                .count();
    }

    private boolean checkBonusMatch(Lotto lotto, int bonusNumber) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
