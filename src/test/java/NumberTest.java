import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @Test
    @DisplayName("입력에 문자열이 포함된 예외 경우")
    void inputExceptionTest1() {
        Assertions.assertThatThrownBy(() -> {
            Number number = new Number("1 2 a");
        }).isInstanceOf(RuntimeException.class).hasMessage("숫자 형식이 아닙니다.");
    }

    @Test
    @DisplayName("음수를 입력받은 경우")
    void inputExceptionTest2() {
        Assertions.assertThatThrownBy(() -> {
                    Number number = new Number("-1 2 3");
                })
                .isInstanceOf(RuntimeException.class)
                .hasMessage("음수가 입력되었습니다.");
    }

}