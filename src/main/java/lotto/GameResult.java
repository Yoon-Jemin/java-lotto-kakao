package lotto;

import java.util.List;

import static lotto.GameStatus.*;

public class GameResult {

    private List<GameStatus> statuses;
    private int profit;
    private double profitRate;

    public GameResult(int profit, double profitRate) {
        this.statuses = List.of(
                FAIL,
                THREE_CORRECT,
                FOUR_CORRECT,
                FIVE_CORRECT,
                FIVE_CORRECT_BONUS, 
                SIX_CORRECT
        );
        this.profit = profit;
        this.profitRate = profitRate;
    }

    public List<GameStatus> getStatuses() {
        return statuses;
    }

    public int getProfit() {
        return profit;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
