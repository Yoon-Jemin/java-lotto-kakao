package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoController {

    private final NumberGenerator randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(
            NumberGenerator randomNumberGenerator,
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
        int lottoCount = price.getLottoCount();
        User user = new User(price, lottoCount);
        int manualLottoCount = makeUserManaulLotto(user);
        outputView.printLottoCountMessage(manualLottoCount, lottoCount - manualLottoCount);
        makeAutoLottoInfo(lottoCount - manualLottoCount, user);

        return user;
    }

    private int makeUserManaulLotto(User user) {
        outputView.printManualLottoCount();
        int manualLottoCount = Integer.parseInt(inputView.inputManualLottoCount());
        List<Lotto> manualLottos = new ArrayList<>();
        outputView.printManualLottoInputMessage();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottos.add(makeManaulLotto());
        }
        user.addLotto(manualLottos);
        return manualLottoCount;
    }

    private Lotto makeManaulLotto() {
        String input = inputView.inputManualLotto();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        return new Lotto(numbers);
    }

    private void makeAutoLottoInfo(int autoLottoCount, User user) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            autoLottos.add(new Lotto(randomNumberGenerator.generate()));
        }
        user.addLotto(autoLottos);
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
        String winningLottoStr = inputView.inputManualLotto();
        return winningLottoStr.split(",");
    }

    private int makeBonusNumber() {
        outputView.printBonusNumberMessage();
        return Integer.parseInt(inputView.inputManualLottoCount());
    }

    private void printResult(LottoResult result) {
        outputView.printStatistics(result.getStatuses());
        outputView.printProfitRate(result.getProfitRate());
    }

}
