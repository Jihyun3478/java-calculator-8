package calculator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calculator.validator.InputValidator;

public class Calculator {
    public int calculate(String input) {
        InputValidator.validateInput(input);

        String[] splits = extractNumbers(input);

        int sum = 0;
        for (String split : splits) {
            if (isNumber(split)) {
                sum += Integer.parseInt(split);
            }
        }
        return sum;
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

    private String[] extractWithCustomDelimiter(String input) {
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
