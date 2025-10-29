package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.InputValidator;

public class InputView {
    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";

    public static int readPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);
        String input = Console.readLine();
        return InputValidator.validateAndParsePurchaseAmount(input);
    }
}
