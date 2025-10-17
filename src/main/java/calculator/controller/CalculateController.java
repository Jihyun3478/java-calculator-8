package calculator.controller;

import calculator.domain.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculateController {
    public void run() {
        String input = InputView.input();

        Calculator calculator = new Calculator();
        int result = calculator.calculate(input);

        OutputView.printResult(result);
    }
}
