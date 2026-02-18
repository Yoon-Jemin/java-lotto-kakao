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

        List<Lotto> manualLottos = makeManualLottos();
        List<Lotto> autoLottos = makeAutoLottos(lottoCount - manualLottos.size());

        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(autoLottos);

        User user = new User(price, allLottos, manualLottos.size());
        outputView.printLottoCountMessage(user.getManualLottoCount(), user.getAutoLottoCount());
        return user;
    }

    private List<Lotto> makeManualLottos() {
        outputView.printManualLottoCount();
        int manualLottoCount = Integer.parseInt(inputView.inputManualLottoCount());

        outputView.printManualLottoInputMessage();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottos.add(makeManualLotto());
        }
        return manualLottos;
    }

    private Lotto makeManualLotto() {
        String input = inputView.inputManualLotto();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        return new Lotto(numbers);
    }

    private List<Lotto> makeAutoLottos(int count) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            autoLottos.add(new Lotto(randomNumberGenerator.generate()));
        }
        return autoLottos;
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
        String winningLottoStr = inputView.inputWinningLotto();
        return winningLottoStr.split(",");
    }

    private int makeBonusNumber() {
        outputView.printBonusNumberMessage();
        return Integer.parseInt(inputView.inputBonusNumber());
    }

    private void printResult(LottoResult result) {
        outputView.printStatistics(result.getStatuses());
        outputView.printProfitRate(result.getProfitRate());
    }

}
