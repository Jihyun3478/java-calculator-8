package calculator.model.service;

import calculator.model.domain.Calculator;

public class CalculateService {
    public int calculate(String input) {
        Calculator calculator = Calculator.from(input);
        return calculator.calculate();
    }
}
