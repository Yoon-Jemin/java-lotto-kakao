package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.LottoNumber.*;

public class LottoNumberTest {

    private LottoNumber lottoNumber;

    @Test
    @DisplayName("성공 케이스")
    void success() {
        lottoNumber = new LottoNumber(6);

        Assertions.assertThat(lottoNumber.getNumber()).isEqualTo(6);
    }

    @Test
    @DisplayName("1 ~ 45 범위를 넘어가는 숫자의 로또 번호가 입력된 경우 예외처리 할 수 있다.")
    void fail_rangeError() {
        Assertions.assertThatThrownBy(() -> {
            lottoNumber = new LottoNumber(46);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(NUMBER_OUT_OF_RANGE_EXCEPTION);
    }

}
