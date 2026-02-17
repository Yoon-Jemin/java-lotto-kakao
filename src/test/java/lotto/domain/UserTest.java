package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("성공 케이스")
    void success() {
        Price price = new Price(3000);
        int lottoCount = 3;
        List<Lotto> lottos = new ArrayList<>(
                List.of(
                        new Lotto(List.of(1,2,3,4,5,6)),
                        new Lotto(List.of(1,2,3,4,5,6)),
                        new Lotto(List.of(1,2,3,4,5,6))
                )
        );

        User user = new User(price, lottoCount);
        user.addLotto(lottos);

        assertThat(user.getPrice()).isEqualTo(3000);
        assertThat(user.getLottoCount()).isEqualTo(3);
        assertThat(user.getLottos()).hasSize(3);
    }

    @Test
    @DisplayName("구매할 수 있는 로또의 개수보다 많은 수동 로또 개수가 입력되면 예외를 발생싴니다.")
    void fail_manualLottoCountExceedingException() {
        Assertions.assertThatThrownBy(() -> {
            User user = new User(new Price(3000), 3);
            user.validateManualLottoCount(4);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(User.MANAUL_LOTTO_COUNT_EXCEEDING_EXCEPTION);
    }

    @Test
    @DisplayName("수동 로또 개수에 음수가 입력되면 예외를 발생싴니다.")
    void fail_manualLottoCountNegativeException() {
        Assertions.assertThatThrownBy(() -> {
            User user = new User(new Price(3000), 3);
            user.validateManualLottoCount(-1);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage(User.MANAUL_LOTTO_COUNT_NEGATIVE_EXCEPTION);
    }

}
