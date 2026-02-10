package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GameControllerTest {
    @Test
    @DisplayName("정상 상태")
    void success() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        Lotto winningLottoNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        User user = new User(3000, 3, lottos);
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, 7);
        Random numberGenerator = new RandomNumberGenerator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameController controller = new GameController(
                user,
                winningLotto,
                numberGenerator,
                inputView,
                outputView
        );

        assertThat(controller.getUser()).isNotNull();
        assertThat(controller.getWinningLotto()).isNotNull();
    }

    @Test
    @DisplayName("")
    public void success_1() {
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(7, 8, 9, 10, 11, 12));
        Lotto lotto3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        List<Lotto> lottos = List.of(lotto1, lotto2, lotto3);

        Lotto winningLottoNumber = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        User user = new User(3000, 3, lottos);
        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, 7);
        Random numberGenerator = new FixedNumberGenerator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        GameController controller = new GameController(
                user,
                winningLotto,
                numberGenerator,
                inputView,
                outputView
        );

        GameResult gameResult = controller.play();

        Assertions.assertThat(gameResult).isNotNull();
    }

    static class FixedNumberGenerator implements Random {

        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }
}
