package calculator.domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public int calculate(String input) {
        validateInput(input);
        String[] splits = extractNumbers(input);

        int sum = 0;
        for (String split : splits) {
            if (isNumber(split)) {
                sum += Integer.parseInt(split);
            }
        }
        return sum;
    }

    private void validateInput(String input) {
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

    private void validateDefaultFormat(String input) {
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

    private String[] extractNumbers(String input) {
        if (input.contains("\\n")) {
            input = input.replace("\\n", "\n");
        }

        if (input.contains("//") && input.contains("\n")) {
            return extractWithCustomDelimiter(input);
        }
        return input.splitWithDelimiters("[,:]", 0);
    }

    private static String[] extractWithCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile("//(.*?)\\n");
        Matcher matcher = pattern.matcher(input);

        String delimiter = "";
        while (matcher.find()) {
            delimiter = matcher.group(1);
        }
        String numbers = input.substring(input.indexOf("\n") + 1);
        return numbers.splitWithDelimiters("[" + delimiter + "]", 0);
    }

    private boolean isNumber(String split) {
        return split.matches("[0-9]+");
    }
}
