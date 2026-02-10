package lotto;

public enum GameStatus {

    SIX_CORRECT(2000000000),
    FIVE_CORRECT_BOUS(30000000),
    FIVE_CORRECT(1500000),
    FOUR_CORRECT(50000),
    THREE_CORRECT(5000),
    FAIL(0)
    ;

    private int price;

    GameStatus(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
