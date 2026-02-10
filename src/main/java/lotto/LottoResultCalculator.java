package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoResultCalculator {

    private final User user;
    private final WinningLotto winningLotto;
    private final Set<Integer> winningLottoSet;

    public LottoResultCalculator(User user, WinningLotto winningLotto) {
        this.user = user;
        this.winningLotto = winningLotto;
        this.winningLottoSet = new HashSet<>(winningLotto.getLotto().getNumbers());
    }

    public GameResult calculate() {



        return null;
//        return new GameResult();
    }

    private List<GameStatus> judgeGameStatus() {



        List<Lotto> lottos = user.getLottos();
        for (Lotto lotto : lottos) {
            Set<Integer> userSet = new HashSet<>(lotto.getNumbers());
            userSet.retainAll(winningLottoSet);
            int count = userSet.size();
        }
    }
}
