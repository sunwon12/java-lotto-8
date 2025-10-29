package lotto.validator;

import lotto.constant.LottoConstants;

import java.util.Arrays;
import java.util.List;

public class InputValidator {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_INVALID_AMOUNT = ERROR_PREFIX + "구입 금액은 1,000원 단위로 입력해야 합니다.";
    private static final String ERROR_POSITIVE_AMOUNT = ERROR_PREFIX + "구입 금액은 양수여야 합니다.";
    private static final String ERROR_NOT_A_NUMBER = ERROR_PREFIX + "구입 금액은 숫자여야 합니다.";
    private static final String ERROR_INVALID_WINNING_NUMBERS = ERROR_PREFIX + "당첨 번호는 쉼표로 구분된 6개의 숫자여야 합니다.";
    private static final String ERROR_INVALID_BONUS_NUMBER = ERROR_PREFIX + "보너스 번호는 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_RANGE = ERROR_PREFIX + "보너스 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_DUPLICATE = ERROR_PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final String DELIMITER = ",";

    public static int validateAndParsePurchaseAmount(String input) {
        int amount = parseAmount(input);
        validatePositiveAmount(amount);
        validateAmountUnit(amount);
        return amount;
    }

    public static List<Integer> validateAndParseWinningNumbers(String input) {
        String[] tokens = input.trim().split(DELIMITER);
        validateWinningNumbersCount(tokens);
        return parseWinningNumbers(tokens);
    }

    public static int validateAndParseBonusNumber(String input, List<Integer> winningNumbers) {
        int bonusNumber = parseBonusNumber(input);
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(bonusNumber, winningNumbers);
        return bonusNumber;
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

    private static void validateWinningNumbersCount(String[] tokens) {
        if (tokens.length != LottoConstants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_INVALID_WINNING_NUMBERS);
        }
    }

    private static List<Integer> parseWinningNumbers(String[] tokens) {
        try {
            return Arrays.stream(tokens)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_WINNING_NUMBERS);
        }
    }

    private static int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_BONUS_NUMBER);
        }
    }

    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < LottoConstants.LOTTO_RANGE_MIN || bonusNumber > LottoConstants.LOTTO_RANGE_MAX) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_RANGE);
        }
    }

    private static void validateBonusNumberDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_NUMBER_DUPLICATE);
        }
    }
}
