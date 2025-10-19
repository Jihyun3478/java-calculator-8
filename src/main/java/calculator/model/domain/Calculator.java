package calculator.model.domain;

import java.util.List;

import calculator.parser.NumberParser;
import calculator.validator.InputValidator;

public class Calculator {
    private final List<Integer> numbers;

    public Calculator(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static Calculator from(String input) {
        InputValidator.validate(input);
        List<Integer> extractNumbers = NumberParser.parseNumbers(input);
        return new Calculator(extractNumbers);
    }

    public int calculate() {
        return numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
    }
}
