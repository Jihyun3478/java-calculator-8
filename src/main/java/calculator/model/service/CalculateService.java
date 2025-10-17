package calculator.model.service;

import calculator.model.domain.Calculator;

public class CalculateService {
    private final Calculator calculator = new Calculator();

    public int calculate(String input) {
        return calculator.calculate(input);
    }
}
