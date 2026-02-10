package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

    private User user;
    private WinningLotto winningLotto;
    private final Random randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(
            Random randomNumberGenerator,
            InputView inputView,
            OutputView outputView
    ) {
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
        makeUserInfo();
        makeWinningLotto();
        LottoResultCalculator calculator = new LottoResultCalculator(user, winningLotto);
        return calculator.calculate();
    }

    private void makeWinningLotto() {
        String[] winningLottoArray = makeWinningLottoNumbers();

        List<Integer> winningLottoList = Arrays.stream(winningLottoArray)
                .map(Integer::parseInt)
                .toList();

        int bonusNumber = makeBonusNumber();
        winningLotto = new WinningLotto(new Lotto(winningLottoList), bonusNumber);
    }

    private int makeBonusNumber() {
        outputView.printMessage("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(inputView.input());
    }

    private String[] makeWinningLottoNumbers() {
        outputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoStr = inputView.input();
        return winningLottoStr.split(",");
    }

    private void makeUserInfo() {
        outputView.printMessage("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(inputView.input());
        int lottoCount = price / 1000;
        outputView.printMessage(lottoCount + "개를 구매했습니다.");

        makeUserLottoInfo(lottoCount, price);
    }

    private void makeUserLottoInfo(int lottoCount, int price) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(randomNumberGenerator.generate()));
        }

        this.user = new User(price, lottoCount, lottos);
        for (Lotto lotto : user.getLottos()) {
            outputView.printLog(lotto.getNumbers());
        }
    }

}
