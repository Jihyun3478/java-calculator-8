package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    @DisplayName("기본 구분자로 숫자를 추출해 합을 계산한다.")
    public void 기본_구분자로_추출해_계산() {
        String input = "1,2:3";
        Calculator calculator = new Calculator();
        int result = calculator.calculate(input);

        assertEquals(6, result);
    }

    @Test
    @DisplayName("커스텀 구분자로 숫자를 추출해 합을 계산한다.")
    public void 커스텀_구분자로_추출해_계산() {
        String input = "//;\n4;5;6";
        Calculator calculator = new Calculator();
        int result = calculator.calculate(input);

        assertEquals(15, result);
    }
}
