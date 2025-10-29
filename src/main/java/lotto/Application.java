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
        int bonusNumber = InputView.readBonusNumber(winningNumbersList);
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersList, bonusNumber);

        LottoGame game = LottoGame.create(purchaseAmount, winningNumbers);
        OutputView.printLottos(game.getLottos());

        LottoResult result = game.play();
        OutputView.printResult(result, purchaseAmount);
    }
}
