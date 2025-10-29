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
}
