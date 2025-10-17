package calculator.service;

import calculator.domain.Calculator;

public class CalculateService {
    private final Calculator calculator = new Calculator();

    public int calculate(String input) {
        return calculator.calculate(input);
    }
}
