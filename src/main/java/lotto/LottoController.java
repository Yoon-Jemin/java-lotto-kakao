package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final Random randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(
            Random randomNumberGenerator,
            InputView inputView,
            OutputView outputView
    ) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public LottoResult play() {
        User user = makeUserInfo();
        printUserLotto(user);
        WinningLotto winningLotto = makeWinningLotto();
        LottoResult result = winningLotto.calculate(user.getLottos(), user.getPrice());
        printResult(result);

        return result;
    }

    private User makeUserInfo() {
        outputView.printPriceMessage();
        Price price = new Price(Integer.parseInt(inputView.inputPrice()));
        int lottoCount = price.getPrice() / 1000;
        outputView.printLottoCountMessage(lottoCount);
        List<Lotto> lottos = makeUserLottoInfo(lottoCount);

        return new User(price, lottoCount, lottos);
    }

    private List<Lotto> makeUserLottoInfo(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(randomNumberGenerator.generate()));
        }

        return lottos;
    }

    private void printUserLotto(User user) {
        for (Lotto lotto : user.getLottos()) {
            outputView.printLog(lotto.getNumbers());
        }
    }

    private WinningLotto makeWinningLotto() {
        String[] winningLottoArray = makeWinningLottoNumbers();
        List<Integer> winningLottoList = Arrays.stream(winningLottoArray)
                .map(Integer::parseInt)
                .toList();

        int bonusNumber = makeBonusNumber();
        return new WinningLotto(new Lotto(winningLottoList), new LottoNumber(bonusNumber));
    }

    private String[] makeWinningLottoNumbers() {
        outputView.printWinningLottoMessage();
        String winningLottoStr = inputView.input();
        return winningLottoStr.split(",");
    }

    private int makeBonusNumber() {
        outputView.printBonusNumberMessage();
        return Integer.parseInt(inputView.input());
    }

    private void printResult(LottoResult result) {
        outputView.printStatistics(result.getStatuses());
        outputView.printProfitRate(result.getProfitRate());
    }

}
