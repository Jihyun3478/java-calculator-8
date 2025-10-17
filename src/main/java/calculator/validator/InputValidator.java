package calculator.validator;

import java.util.Objects;

public class InputValidator {
    private static final String EMPTY_INPUT = "입력값이 비어있습니다.";
    private static final String NOT_POSITIVE_INPUT = "입력값은 양수이어야 합니다.";
    private static final String INVALID_INPUT = "잘못된 형식입니다.";

    public static void validate(String input) {
        validateEmpty(input);
        validatePositive(input);
        if (!(input.contains("//") && (input.contains("\n") || input.contains("\\n")))) {
            validateOnlyDelimiter(input);
            validatePrimaryDelimiter(input);
            validateSequenceDelimiter(input);
        }
    }

    private static void validateEmpty(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }
    }

    private static void validatePositive(String input) {
        if (input.contains("0") || input.contains("-1")) {
            throw new IllegalArgumentException(NOT_POSITIVE_INPUT);
        }
    }

    private static void validateOnlyDelimiter(String input) {
        if (input.matches("[,:]+")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    private static void validatePrimaryDelimiter(String input) {
        if (input.matches(".*[^0-9,:].*")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    private static void validateSequenceDelimiter(String input) {
        if (input.matches(".*[,:]{2,}.*")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
