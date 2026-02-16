package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    @DisplayName("커스텀 구분자가 없는 정상 입력인 경우")
    void success() {
        String successInput = "1,2,3";
        Parser parser = new Parser(successInput);

        Assertions.assertThat(parser.getNumber().getNumbers()).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("커스텀 구분자가 있는 정상 입력인 경우")
    void success_customSeparator() {
        String successInput = "//;\n1,2,3";
        Parser parser = new Parser(successInput);

        Assertions.assertThat(parser.getNumber().getNumbers()).containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("등록한 구분자 이외의 구분자를 사용한 경우")
    void inputExceptionTest3() {
        Assertions.assertThatThrownBy(() -> {
            Parser parser = new Parser("1^2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("등록되지 않은 커스텀 구분자가 입력되었습니다.");
    }

    @Test
    @DisplayName("구분자를 연속적으로 사용한 경우")
    void inputExceptionTest6() {
        Assertions.assertThatThrownBy(() -> {
            Parser parser = new Parser("1,2,,3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}