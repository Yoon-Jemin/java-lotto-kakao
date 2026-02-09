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
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수를 입력받은 경우")
    void inputExceptionTest2() {
        Assertions.assertThatThrownBy(() -> {
            UserInput input = new UserInput("-1,2,3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("등록한 구분자 이외의 구분자를 사용한 경우")
    void inputExceptionTest3() {
        Assertions.assertThatThrownBy(() -> {
            UserInput input = new UserInput("1^2,3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자인 경우")
    void inputExceptionTest4() {
        Assertions.assertThatThrownBy(() -> {
            UserInput input = new UserInput("//6\n1,2,a");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자인 경우")
    void inputExceptionTest5() {
        Assertions.assertThatThrownBy(() -> {
            UserInput input = new UserInput("///;\n1;2;3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구분자를 연속적으로 사용한 경우")
    void inputExceptionTest6() {
        Assertions.assertThatThrownBy(() -> {
            UserInput input = new UserInput("1,2,,3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}