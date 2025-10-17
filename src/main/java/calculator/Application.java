package calculator;

import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        String input = InputView.input();

        Calculator calculator = new Calculator();
        int result = calculator.calculate(input);

        OutputView.printResult(result);
    }
}
