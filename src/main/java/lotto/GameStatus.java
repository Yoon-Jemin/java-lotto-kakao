package lotto;

public enum GameStatus {

    SIX_CORRECT(2000000000, 0),
    FIVE_CORRECT_BONUS(30000000, 0),
    FIVE_CORRECT(1500000, 0),
    FOUR_CORRECT(50000, 0),
    THREE_CORRECT(5000, 0),
    FAIL(0, 0)
    ;

    private int price;
    private int count;

    GameStatus(int price, int count) {
        this.price = price;
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
