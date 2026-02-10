package lotto;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum GameStatus {

    SIX_CORRECT(2000000000, 6, false),
    FIVE_CORRECT_BONUS(30000000, 5, true),
    FIVE_CORRECT(1500000, 5, false),
    FOUR_CORRECT(50000, 4, false),
    THREE_CORRECT(5000, 3, false),
    FAIL(0, -1, false)
    ;

    private int price;
    private int count;
    private boolean hasBonus;

    GameStatus(int price, int count, boolean hasBonus) {
        this.price = price;
        this.count = count;
        this.hasBonus = hasBonus;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public static GameStatus judgeGameStatus(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(status -> status.match(matchCount, hasBonus))
                .findFirst()
                .orElse(FAIL);
    }

    public boolean match(int matchCount, boolean hasBonus) {
        if (this.count != matchCount) return false;
        if (this.count == 5) return this.hasBonus == hasBonus;
        return true;
    }

}
