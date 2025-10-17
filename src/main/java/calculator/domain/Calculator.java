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
        InputValidator.validateInput(input);
        List<String> extractNumbers = extractNumbers(input);

        return extractNumbers.stream()
            .filter(this::isNumber)
            .mapToInt(Integer::parseInt).sum();
    }

    public List<String> extractNumbers(String input) {
        if (input.contains("\\n")) {
            input = input.replace("\\n", "\n");
        }

        if (input.contains("//") && input.contains("\n")) {
            return extractWithCustomDelimiter(input);
        }

        String[] numbers = input.splitWithDelimiters("[,:]", 0);
        return Arrays.stream(numbers).toList();
    }

    private List<String> extractWithCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(input);

        String delimiter = "";
        while (matcher.find()) {
            delimiter = matcher.group(1);
        }
        String numbers = input.substring(input.indexOf("\n") + 1);
        String[] splitNumbers = numbers.splitWithDelimiters("[" + delimiter + "]", 0);
        return Arrays.stream(splitNumbers).toList();
    }

    private boolean isNumber(String split) {
        return split.matches(NUMBER_REGEX);
    }
}
