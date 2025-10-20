package calculator.model.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"1,2:3", "1,2,3", "1:2:3"})
    @DisplayName("기본 구분자로 숫자를 추출해 합을 계산한다.")
    public void 기본_구분자로_추출해_계산(String input) {
        Calculator calculator = Calculator.from(input);
        int result = calculator.calculate();

        assertEquals(6, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\n4;5;6", "//.\n4.5.6"})
    public void 커스텀_구분자로_추출해_계산(String input) {
        Calculator calculator = Calculator.from(input);
        int result = calculator.calculate();

        assertEquals(15, result);
    }

    @Test
    @DisplayName("커스텀 구분자는 숫자도 가능하다.")
    public void 커스텀_구분자는_숫자도_가능() {
        String input = "//1\n71819";
        Calculator calculator = Calculator.from(input);
        int result = calculator.calculate();

        assertEquals(24, result);
    }

    @Test
    @DisplayName("숫자 1개만 입력하면 그 숫자를 반환한다")
    void 숫자_1개() {
        Calculator calculator = Calculator.from("5");
        assertThat(calculator.calculate()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("입력된 값이 없는 경우, 예외가 발생한다.")
    public void 입력된_값이_없는_경우_예외_발생(String input) {
        assertAll(
            () -> assertThatThrownBy(() -> Calculator.from(input)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> Calculator.from(null)).isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1,2;3", "4,5;6"})
    @DisplayName("숫자가 양수가 아닌 경우, 예외가 발생한다.")
    public void 숫자가_양수가_아닌_경우_예외_발생(String input) {
        assertThatThrownBy(() -> Calculator.from(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {",;", "1*2?3", "1,,2;3"})
    @DisplayName("잘못된 형식인 경우, 예외가 발생한다.")
    public void 잘못된_형식인_경우_예외_발생(String input) {
        assertThatThrownBy(() -> Calculator.from(input)).isInstanceOf(IllegalArgumentException.class);
    }
}
