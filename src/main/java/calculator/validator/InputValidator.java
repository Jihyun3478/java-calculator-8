package calculator.validator;

import static calculator.constant.ErrorMessage.*;

import java.util.Objects;

public class InputValidator {
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
            throw new IllegalArgumentException(EMPTY_INPUT.getMessage());
        }
    }

    private static void validatePositive(String input) {
        if (input.contains("0") || input.contains("-1")) {
            throw new IllegalArgumentException(NOT_POSITIVE_INPUT.getMessage());
        }
    }

    private static void validateOnlyDelimiter(String input) {
        if (input.matches("[,:]+")) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static void validatePrimaryDelimiter(String input) {
        if (input.matches(".*[^0-9,:].*")) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static void validateSequenceDelimiter(String input) {
        if (input.matches(".*[,:]{2,}.*")) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }
}
