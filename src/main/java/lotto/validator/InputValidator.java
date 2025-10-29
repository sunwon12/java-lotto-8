package lotto.validator;

import lotto.constant.LottoConstants;

public class InputValidator {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_AMOUNT = ERROR_PREFIX + "구입 금액은 1,000원 단위로 입력해야 합니다.";
    private static final String ERROR_POSITIVE_AMOUNT = ERROR_PREFIX + "구입 금액은 양수여야 합니다.";
    private static final String ERROR_NOT_A_NUMBER = ERROR_PREFIX + "구입 금액은 숫자여야 합니다.";

    public static int validateAndParsePurchaseAmount(String input) {
        int amount = parseAmount(input);
        validatePositiveAmount(amount);
        validateAmountUnit(amount);
        return amount;
    }

    private static int parseAmount(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_A_NUMBER);
        }
    }

    private static void validatePositiveAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_POSITIVE_AMOUNT);
        }
    }

    private static void validateAmountUnit(int amount) {
        if (amount % LottoConstants.PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException(ERROR_INVALID_AMOUNT);
        }
    }
}
