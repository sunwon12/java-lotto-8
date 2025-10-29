package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.InputValidator;

import java.util.List;

public class InputView {
    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String PROMPT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        System.out.println(PROMPT_PURCHASE_AMOUNT);
        String input = Console.readLine();
        return InputValidator.validateAndParsePurchaseAmount(input);
    }

    public static List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println(PROMPT_WINNING_NUMBERS);
        String input = Console.readLine();
        return InputValidator.validateAndParseWinningNumbers(input);
    }

    public static int readBonusNumber(List<Integer> winningNumbers) {
        System.out.println();
        System.out.println(PROMPT_BONUS_NUMBER);
        String input = Console.readLine();
        return InputValidator.validateAndParseBonusNumber(input, winningNumbers);
    }
}
