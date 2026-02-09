import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {

    @Test
    @DisplayName("커스텀 구분자가 없는 정상 입력인 경우")
    void inputTest1() {
        UserInput input = new UserInput("1,2,3");
        Number number = input.getNumber();
        Separator separator = input.getSeparator();

        Assertions.assertThat(number.getNumbers()).containsExactly(1,2,3);
        Assertions.assertThat(separator.getSeparators()).contains(",", ":");
    }

    @Test
    @DisplayName("커스텀 구분자가 포함된 정상 입력인 경우")
    void inputTest2() {
        UserInput input = new UserInput("//;\n,1,2,3");
        Number number = input.getNumber();
        Separator separator = input.getSeparator();

        Assertions.assertThat(number.getNumbers()).containsExactly(1,2,3);
        Assertions.assertThat(separator.getSeparators()).contains(",", ":", ";");
    }

    @Test
    @DisplayName("입력에 문자열이 포함된 예외 경우")
    void inputExceptionTest1() {
        Assertions.assertThatThrownBy(() -> {
            UserInput input = new UserInput("1,2,a");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("문자열을 입력받았습니다.");
    }

}