package calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberParser {
    private static final String LF = "\n";
    private static final String CUSTOM_DELIMITER_REGEX = "//(.)\\n";

    public static List<Integer> parseNumbers(String input) {
        input = input.replace("\\n", LF);

        if (isCustomDelimiter(input)) {
            return parseWithCustomDelimiter(input);
        }
        return splitToList(input, "[,:]");
    }

    private static List<Integer> parseWithCustomDelimiter(String input) {
        Pattern pattern = Pattern.compile(CUSTOM_DELIMITER_REGEX);
        Matcher matcher = pattern.matcher(input);

        String delimiter = "";
        while (matcher.find()) {
            delimiter = matcher.group(1);
        }

        int delimiterIndex = input.indexOf(LF);
        String numbers = input.substring(delimiterIndex + 1);

        return splitToList(numbers, "[" + delimiter + "]");
    }

    private static boolean isCustomDelimiter(String input) {
        return input.contains("//") && input.contains(LF);
    }

    private static List<Integer> splitToList(String input, String regex) {
        String[] tokens = input.split(regex);

        return Arrays.stream(tokens)
            .filter(t -> t.matches("\\d+"))
            .map(Integer::parseInt)
            .toList();
    }
}
