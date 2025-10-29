package lotto;

import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        Lottos lottos = Lottos.generateLottos(purchaseAmount);
        OutputView.printLottos(lottos);

        List<Integer> winningNumbersList = InputView.readWinningNumbers();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersList);

        int bonusNumber = InputView.readBonusNumber(winningNumbersList);

        LottoResult lottoResult = calculateResult(lottos, winningNumbers, bonusNumber);
        OutputView.printResult(lottoResult, purchaseAmount);
    }

    private static LottoResult calculateResult(Lottos lottos, WinningNumbers winningNumbers, int bonusNumber) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos.getLottos()) {
            LottoRank rank = winningNumbers.match(lotto, bonusNumber);
            lottoResult.addResult(rank);
        }
        return lottoResult;
    }
}
