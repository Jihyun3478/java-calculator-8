package calculator.validator;

import java.util.Objects;

public class InputValidator {
    private static final String EMPTY_INPUT = "잘못된 형식입니다.";
    private static final String NOT_POSITIVE_INPUT = "잘못된 형식입니다.";
    private static final String INVALID_INPUT = "잘못된 형식입니다.";

    public static void validateInput(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }

        if (input.contains("0") || input.contains("-1")) {
            throw new IllegalArgumentException(NOT_POSITIVE_INPUT);
        }

        if (!(input.contains("//") && (input.contains("\n") || input.contains("\\n")))) {
            validateDefaultFormat(input);
        }
    }

    private static void validateDefaultFormat(String input) {
        if (input.matches("[,:]+")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        if (input.matches(".*[^0-9,:].*")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }

        if (input.matches(".*[,:]{2,}.*")) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }
}
