package lotto.view;

import lotto.Lotto;
import lotto.domain.Lottos;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println();
        System.out.println(lottos.getCount() + "개를 구매했습니다.");
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }
}
