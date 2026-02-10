package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

    private final User user;
    private WinningLotto winningLotto;
    private final Random randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(
            User user,
            Random randomNumberGenerator,
            InputView inputView,
            OutputView outputView
    ) {
        this.user = user;
        this.randomNumberGenerator = randomNumberGenerator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public User getUser() {
        return user;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }

    public GameResult play() {
        outputView.printMessage("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(inputView.input());
        int lottoCount = price / 1000;
        outputView.printMessage(lottoCount + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(randomNumberGenerator.generate()));
        }

        User user = new User(price, lottoCount, lottos);
        for (Lotto lotto : user.getLottos()) {
            outputView.printLog(lotto.getNumbers());
        }

        outputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoStr = inputView.input();
        String[] winningLottoArray = winningLottoStr.split(",");

        List<Integer> winningLottoList = Arrays.stream(winningLottoArray)
                .map(Integer::parseInt)
                .toList();

        outputView.printMessage("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(inputView.input());

        winningLotto = new WinningLotto(new Lotto(winningLottoList), bonusNumber);

        return new GameResult(GameStatus.FOUR_CORRECT, 10000, 1);
    }
}
