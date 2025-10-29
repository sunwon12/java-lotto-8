package lotto;

import lotto.domain.LottoGame;
import lotto.domain.LottoResult;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        List<Integer> winningNumbersList = InputView.readWinningNumbers();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersList);
        int bonusNumber = InputView.readBonusNumber(winningNumbersList);

        LottoGame game = LottoGame.create(purchaseAmount, winningNumbers, bonusNumber);
        OutputView.printLottos(game.getLottos());

        LottoResult result = game.play();
        OutputView.printResult(result, purchaseAmount);
    }
}
