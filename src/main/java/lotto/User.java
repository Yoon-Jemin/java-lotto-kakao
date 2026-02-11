package lotto;

import java.util.List;

public class User {

    private Price price;
    private int lottoCount;
    private List<Lotto> lottos;

    public User(Price price, int lottoCount, List<Lotto> lottos) {
        this.price = price;
        this.lottoCount = lottoCount;
        this.lottos = lottos;
    }

    public int getPrice() {
        return price.getPrice();
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
