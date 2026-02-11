package lotto;

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

        User user = new User(price, lottoCount, lottos);

        assertThat(user.getPrice()).isEqualTo(3000);
        assertThat(user.getLottoCount()).isEqualTo(3);
        assertThat(user.getLottos()).hasSize(3);
    }

}
