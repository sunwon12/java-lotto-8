package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

import java.util.Arrays;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.getCount() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printResult(LottoResult lottoResult, int purchaseAmount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printRankStatistics(lottoResult);
        printProfitRate(lottoResult, purchaseAmount);
    }

    private static void printRankStatistics(LottoResult lottoResult) {
        Arrays.stream(LottoRank.values())
                .filter(LottoRank::isWinning)
                .sorted((r1, r2) -> Integer.compare(r2.getPrizeMoney(), r1.getPrizeMoney()))
                .forEach(rank -> {
                    int count = lottoResult.getCount(rank);
                    System.out.println(rank.getDescription() + " - " + count + "개");
                });
    }

    private static void printProfitRate(LottoResult lottoResult, int purchaseAmount) {
        double profitRate = (double) lottoResult.getTotalPrizeMoney() / purchaseAmount * 100;
        System.out.println("총 수익률은 " + String.format("%.1f", profitRate) + "%입니다.");
    }
}
