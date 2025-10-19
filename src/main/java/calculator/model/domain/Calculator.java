package calculator.model.domain;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import calculator.validator.InputValidator;

public class Calculator {
    private static final String CUSTOM_DELIMITER_REGEX = "//(.*?)\\n";
    private static final String NUMBER_REGEX = "[0-9]+";

    public int calculate(String input) {
        InputValidator.validate(input);
        List<Integer> extractNumbers = parseNumbers(input);

        return extractNumbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public List<Integer> parseNumbers(String input) {
        input = input.replace("\\n", "\n");

        if (input.contains("//") && input.contains("\n")) {
            return parseWithCustomDelimiter(input);
        }
        return splitToList(input, "[,:]");
    }

    private List<Integer> parseWithCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(input);

        String delimiter = "";
        while (matcher.find()) {
            delimiter = matcher.group(1);
        }
        String numbers = input.substring(input.indexOf("\n") + 1);
        return splitToList(numbers, "[" + delimiter + "]");
    }

    private List<Integer> splitToList(String input, String regex) {
        String[] tokens = input.splitWithDelimiters(regex, 0);

        return IntStream.range(0, tokens.length)
            .filter(i -> i % 2 == 0 && isNumber(tokens[i]))
            .mapToObj(i -> Integer.parseInt(tokens[i]))
            .toList();
    }

    private boolean isNumber(String split) {
        return split.matches(NUMBER_REGEX);
    }
}
