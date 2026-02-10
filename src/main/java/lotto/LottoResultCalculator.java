package lotto;

import java.util.*;

public class LottoResultCalculator {

    private final User user;
    private final WinningLotto winningLotto;
    private final Set<Integer> winningLottoSet;

    public LottoResultCalculator(User user, WinningLotto winningLotto) {
        this.user = user;
        this.winningLotto = winningLotto;
        this.winningLottoSet = new HashSet<>(winningLotto.getLotto().getNumbers());
    }

    public GameResult calculate() {
        HashMap<GameStatus, Integer> gameResultMap = getGameStatusIntegerHashMap();
        long profit = calculateProfit(gameResultMap);
        double profitRate = calculateProfitRate(profit);

        return new GameResult(gameResultMap, profit, profitRate);
    }

    private HashMap<GameStatus, Integer> getGameStatusIntegerHashMap() {
        HashMap<GameStatus, Integer> gameResultMap = new HashMap<>();

        for (Lotto userLotto : user.getLottos()) {
            Set<Integer> userSet = new HashSet<>(userLotto.getNumbers());
            userSet.retainAll(winningLottoSet);
            int count = userSet.size();
            boolean hasBonus = checkBonusNumber(userLotto);
            GameStatus gameStatus = GameStatus.judgeGameStatus(count, hasBonus);

            gameResultMap.put(gameStatus, gameResultMap.getOrDefault(gameStatus, 0) + 1);
        }
        return gameResultMap;
    }

    private double calculateProfitRate(long profit) {
        return (double) profit / user.getPrice();
    }

    private long calculateProfit(Map<GameStatus, Integer> map) {
        long profit = 0;
        for (GameStatus gameStatus : map.keySet()) {
            profit += gameStatus.getPrice() * map.get(gameStatus);
        }

        return profit;
    }

    private boolean checkBonusNumber(Lotto userLotto) {
        Set<Integer> userLottoSet = new HashSet<>(userLotto.getNumbers());
        return userLottoSet.contains(winningLotto.getBonusNumber());
    }
}
