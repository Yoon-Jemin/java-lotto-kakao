package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeparatorTest {

    @Test
    @DisplayName("커스텀 구분자가 없는 정상 입력인 경우")
    void success() {
        Separator separator = new Separator("1,2,3");

        Assertions.assertThat(separator.getSeparators()).containsExactly(":", ",");
    }

    @Test
    @DisplayName("커스텀 구분자가 포함된 정상 입력인 경우")
    void success_hasCustomSeparator() {
        Separator separator = new Separator("//;\n1;2,3");

        Assertions.assertThat(separator.getSeparators()).containsExactly(":", ";", ",");
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자인 경우")
    void fail_customSeparatorIsNumber() {
        Assertions.assertThatThrownBy(() -> {
            Separator input = new Separator("//6\n1,2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("커스텀 구분자는 숫자가 될 수 없습니다.");
    }

    @Test
    @DisplayName("커스텀 구분자 등록 형식이 잘못된 경우")
    void fail_customSeparatorFormatError() {
        Assertions.assertThatThrownBy(() -> {
            Separator input = new Separator("///;\n1;2;3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("커스텀 구분자 추가 형식이 잘못되었습니다.");
    }

}