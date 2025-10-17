package calculator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import calculator.domain.Calculator;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("기본 구분자로 숫자를 추출해 합을 계산한다.")
    public void 기본_구분자로_추출해_계산() {
        String input = "1,2:3";
        calculator = new Calculator();
        int result = calculator.calculate(input);

        assertEquals(6, result);
    }

    @Test
    @DisplayName("커스텀 구분자로 숫자를 추출해 합을 계산한다.")
    public void 커스텀_구분자로_추출해_계산() {
        String input = "//;\n4;5;6";
        calculator = new Calculator();
        int result = calculator.calculate(input);

        assertEquals(15, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("입력된 값이 없는 경우, 예외가 발생한다.")
    public void 입력된_값이_없는_경우_예외_발생(String input) {
        calculator = new Calculator();
        assertAll(
            () -> assertThatThrownBy(() -> calculator.calculate(input)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> calculator.calculate(null)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("숫자가 양수가 아닌 경우, 예외가 발생한다.")
    public void 숫자가_양수가_아닌_경우_예외_발생() {
        String input = ",;";
        assertThatThrownBy(() -> calculator.calculate(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {",;", "1*2?3", "1,,2;3"})
    @DisplayName("잘못된 형식인 경우, 예외가 발생한다.")
    public void 잘못된_형식인_경우_예외_발생() {
        String input = ",;";
        assertThatThrownBy(() -> calculator.calculate(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
