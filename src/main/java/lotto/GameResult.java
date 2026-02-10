package lotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.GameStatus.*;

public class GameResult {

    private Map<GameStatus, Integer> statuses;
    private int profit;
    private double profitRate;

    public GameResult(Map<GameStatus, Integer> map, int profit, double profitRate) {
        this.statuses = map;
        this.profit = profit;
        this.profitRate = profitRate;
    }

    public Map<GameStatus, Integer> getStatuses() {
        return statuses;
    }

    public int getProfit() {
        return profit;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
