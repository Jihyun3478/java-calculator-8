package calculator.controller;

import calculator.model.service.CalculateService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculateController {
    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    public void run() {
        String input = InputView.input();
        int result = calculateService.calculate(input);
        OutputView.printResult(result);
    }
}
