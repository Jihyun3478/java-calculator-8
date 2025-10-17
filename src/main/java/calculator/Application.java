package calculator;

import calculator.controller.CalculateController;

public class Application {
    public static void main(String[] args) {
        CalculateController calculateController = new CalculateController();
        calculateController.run();
    }
}
