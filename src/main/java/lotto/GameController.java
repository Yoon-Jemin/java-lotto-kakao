package lotto;

public class GameController {


    private final User user;
    private final WinningLotto winningLotto;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(
            User user,
            WinningLotto winningLotto,
            InputView inputView,
            OutputView outputView
    ) {
        this.user = user;
        this.winningLotto = winningLotto;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public User getUser() {
        return user;
    }

    public WinningLotto getWinningLotto() {
        return winningLotto;
    }
}
