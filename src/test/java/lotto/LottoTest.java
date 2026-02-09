package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoTest {

    @Test
    @DisplayName("정상적으로 로또 번호를 입력한 경우")
    void lottoSuccessTest() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThat(lotto.getNumbers()).hasSize(6);
        Assertions.assertThat(lotto.getNumbers()).containsExactly(1,2,3,4,5,6);
    }
}
