package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> result;

    public LottoResult() {
        this.result = new EnumMap<>(LottoRank.class);
        initializeResult();
    }

    private void initializeResult() {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.isWinning()) {
                result.put(rank, 0);
            }
        }
    }

    public void addResult(LottoRank rank) {
        if (rank.isWinning()) {
            result.put(rank, result.get(rank) + 1);
        }
    }

    public int getCount(LottoRank rank) {
        return result.getOrDefault(rank, 0);
    }

    public long getTotalPrizeMoney() {
        return result.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }
}
