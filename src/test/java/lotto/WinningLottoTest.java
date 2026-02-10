package lotto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WinningLottoTest {

    private User user;

    @BeforeEach
    void beforeEach() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        this.user = new User(3000, 3, lottos);
    }

    @Test
    @DisplayName("성공 케이스")
    void success() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);

        assertThat(winningLotto.getLotto().getNumbers()).containsExactly(1,2,3,4,5,6);
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호가 범위를 벗어나는 경우")
    void fail_bonusNumberRange() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusNumber = 46;
            WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.");
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되는 경우")
    void fail_bonusNumberDuplicate() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonusNumber = 1;
            WinningLotto winningLotto = new WinningLotto(lotto, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.");
    }

    @Test
    @DisplayName("사용자의 로또 번호를 기반으로 수익과 수익률을 계산할 수 있다.")
    void calculate() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        LottoResult result = winningLotto.calculate(this.user.getLottos(), this.user.getPrice());
        long profit = result.getProfit();
        double profitRate = result.getProfitRate();

        assertThat(result.getStatuses().get(LottoStatus.SIX_CORRECT)).isEqualTo(1);
        assertThat(profit).isEqualTo(2000000000);
        assertThat(profitRate).isEqualTo(666666.6666666666);
    }

}
