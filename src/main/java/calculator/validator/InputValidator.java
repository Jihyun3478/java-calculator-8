package calculator.validator;

import java.util.Objects;

public class InputValidator {
    public static void validateInput(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }

        if (input.contains("0") || input.contains("-1")) {
            throw new IllegalArgumentException("입력되는 숫자는 양수이어야 합니다.");
        }

        if (!(input.contains("//") && (input.contains("\n") || input.contains("\\n")))) {
            validateDefaultFormat(input);
        }
    }

    private static void validateDefaultFormat(String input) {
        if (input.matches("[,:]+")) {
            throw new IllegalArgumentException("구분자만 입력되었습니다.");
        }

        if (input.matches(".*[^0-9,:].*")) {
            throw new IllegalArgumentException("잘못된 구분자입니다.");
        }

        if (input.matches(".*[,:]{2,}.*")) {
            throw new IllegalArgumentException("잘못된 형식입니다.");
        }
    }
}
