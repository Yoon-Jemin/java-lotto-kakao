package lotto;

import java.util.*;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto lotto, int bonusNumber) {
        validateRange(bonusNumber);
        validateDuplicate(lotto, bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.");
        }
    }

    private void validateDuplicate(Lotto lotto, int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 중복된 숫자를 보너스 번호로 등록할 수 없습니다.");
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public LottoResult calculate(List<Lotto> userLottos, int price) {
        HashMap<LottoStatus, Integer> gameResultMap = getGameStatusIntegerHashMap(userLottos);
        long profit = calculateProfit(gameResultMap);
        double profitRate = calculateProfitRate(profit, price);

        return new LottoResult(gameResultMap, profit, profitRate);
    }

    private HashMap<LottoStatus, Integer> getGameStatusIntegerHashMap(List<Lotto> userLottos) {
        HashMap<LottoStatus, Integer> gameResultMap = new HashMap<>();

        for (Lotto userLotto : userLottos) {
            Set<Integer> userSet = new HashSet<>(userLotto.getNumbers());
            userSet.retainAll(new HashSet<>(this.lotto.getNumbers()));
            int count = userSet.size();
            boolean hasBonus = checkBonusNumber(userLotto);
            LottoStatus gameStatus = LottoStatus.judgeGameStatus(count, hasBonus);

            gameResultMap.put(gameStatus, gameResultMap.getOrDefault(gameStatus, 0) + 1);
        }
        return gameResultMap;
    }

    private boolean checkBonusNumber(Lotto userLotto) {
        Set<Integer> userLottoSet = new HashSet<>(userLotto.getNumbers());
        return userLottoSet.contains(this.bonusNumber);
    }

    private long calculateProfit(Map<LottoStatus, Integer> map) {
        long profit = 0;
        for (LottoStatus gameStatus : map.keySet()) {
            profit += gameStatus.getPrice() * map.get(gameStatus);
        }

        return profit;
    }

    private double calculateProfitRate(long profit, int price) {
        return (double) profit / price;
    }
}
