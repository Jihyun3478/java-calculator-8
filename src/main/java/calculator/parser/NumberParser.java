package calculator.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class NumberParser {
    private static final String CUSTOM_DELIMITER_REGEX = "//(.*?)\\n";
    private static final String NUMBER_REGEX = "[0-9]+";
    
    public static List<Integer> parseNumbers(String input) {
        input = input.replace("\\n", "\n");

        if (input.contains("//") && input.contains("\n")) {
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

        int delimiterIndex = input.indexOf("\n");
        String numbers = input.substring(delimiterIndex + 1);

        return splitToList(numbers, "[" + delimiter + "]");
    }

    private static List<Integer> splitToList(String input, String regex) {
        String[] tokens = input.splitWithDelimiters(regex, 0);

        return IntStream.range(0, tokens.length)
            .filter(i -> isIndexEven(i) && isNumber(tokens[i]))
            .mapToObj(i -> Integer.parseInt(tokens[i]))
            .toList();
    }

    private static boolean isIndexEven(int i) {
        return i % 2 == 0;
    }

    private static boolean isNumber(String split) {
        return split.matches(NUMBER_REGEX);
    }
}
