package lotto.generator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {
    private final Queue<List<Integer>> numbersQueue;

    public FixedLottoNumberGenerator(List<List<Integer>> numbersList) {
        this.numbersQueue = new LinkedList<>(numbersList);
    }

    @Override
    public List<Integer> generate() {
        if (numbersQueue.isEmpty()) {
            throw new IllegalStateException("미리 설정된 로또 번호가 모두 소진되었습니다.");
        }
        return numbersQueue.poll();
    }
}
