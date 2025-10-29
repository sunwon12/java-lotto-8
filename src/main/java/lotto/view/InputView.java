package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.InputValidator;

import java.util.List;

public class InputView {
    private static final String PROMPT_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String PROMPT_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String PROMPT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        while (true) {
            try {
                System.out.println(PROMPT_PURCHASE_AMOUNT);
                String input = Console.readLine();
                return InputValidator.validateAndParsePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Integer> readWinningNumbers() {
        while (true) {
            try {
                System.out.println();
                System.out.println(PROMPT_WINNING_NUMBERS);
                String input = Console.readLine();
                return InputValidator.validateAndParseWinningNumbers(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int readBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                System.out.println();
                System.out.println(PROMPT_BONUS_NUMBER);
                String input = Console.readLine();
                return InputValidator.validateAndParseBonusNumber(input, winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
