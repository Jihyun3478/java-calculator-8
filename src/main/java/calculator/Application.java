package calculator;

import calculator.controller.CalculateController;
import calculator.service.CalculateService;

public class Application {
    public static void main(String[] args) {
        CalculateController calculateController = new CalculateController(new CalculateService());
        calculateController.run();
    }
}
