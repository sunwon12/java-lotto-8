package lotto;

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
    }
}
