package lotto;

public class GameResult {

    private GameStatus gameStatus;
    private int profit;
    private double profitRate;

    public GameResult(GameStatus gameStatus, int profit, double profitRate) {
        this.gameStatus = gameStatus;
        this.profit = profit;
        this.profitRate = profitRate;
    }
}
