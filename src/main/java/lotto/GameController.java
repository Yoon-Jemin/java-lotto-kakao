package lotto;

public class GameController {


    private final User user;
    private final WinningLotto winningLotto;
    private final Random randomNumberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public GameController(
            User user,
            WinningLotto winningLotto,
            Random randomNumberGenerator,
            InputView inputView,
            OutputView outputView
    ) {
        this.user = user;
        this.winningLotto = winningLotto;
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
        return new GameResult(GameStatus.FOUR_CORRECT, 10000, 1);
    }
}
