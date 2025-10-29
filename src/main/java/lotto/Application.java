package lotto;

import lotto.domain.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        Lottos lottos = Lottos.generateLottos(purchaseAmount);
        OutputView.printLottos(lottos);
    }
}
