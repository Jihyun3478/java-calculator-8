package calculator.domain;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.validator.InputValidator;

public class Calculator {
    private static final String CUSTOM_DELIMITER_REGEX = "//(.*?)\\n";
    private static final String NUMBER_REGEX = "[0-9]+";

    public int calculate(String input) {
        InputValidator.validate(input);
        List<String> extractNumbers = parseNumbers(input);

        return extractNumbers.stream()
            .filter(this::isNumber)
            .mapToInt(Integer::parseInt).sum();
    }

    public List<String> parseNumbers(String input) {
        if (input.contains("\\n")) {
            input = input.replace("\\n", "\n");
        }

        if (input.contains("//") && input.contains("\n")) {
            return parseWithCustomDelimiter(input);
        }
        return splitToList(input, "[,:]");
    }

    private List<String> parseWithCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(input);

        String delimiter = "";
        while (matcher.find()) {
            delimiter = matcher.group(1);
        }
        String numbers = input.substring(input.indexOf("\n") + 1);
        return splitToList(numbers, "[" + delimiter + "]");
    }

    private boolean isNumber(String split) {
        return split.matches(NUMBER_REGEX);
    }

    private List<String> splitToList(String input, String regex) {
        String[] numbers = input.splitWithDelimiters(regex, 0);
        return Arrays.stream(numbers).toList();
    }
}
