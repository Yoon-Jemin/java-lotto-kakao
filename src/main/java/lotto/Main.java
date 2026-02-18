package lotto;

import lotto.domain.NumberGenerator;
import lotto.domain.RandomNumberGenerator;
import lotto.view.CommandInputView;
import lotto.view.CommandOutputView;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new CommandInputView();
        OutputView outputView = new CommandOutputView();
        NumberGenerator generator = new RandomNumberGenerator();

        LottoController controller = new LottoController(generator, inputView, outputView);

        controller.play();
    }
}
