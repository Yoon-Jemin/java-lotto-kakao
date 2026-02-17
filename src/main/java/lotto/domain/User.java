package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static String MANAUL_LOTTO_COUNT_EXCEEDING_EXCEPTION = "구매할 수 있는 로또의 개수보다 많은 개수가 입력되었습니다.";

    private Price price;
    private int lottoCount;
    private List<Lotto> lottos;

    public User(Price price, int lottoCount) {
        this.price = price;
        this.lottoCount = lottoCount;
        this.lottos = new ArrayList<>();
    }

    public void addLotto(List<Lotto> newLottos) {
        this.lottos.addAll(newLottos);
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

    public void validateManualLottoCount(int manualLottoCount) {
        if (lottoCount < manualLottoCount) {
            throw new IllegalArgumentException(MANAUL_LOTTO_COUNT_EXCEEDING_EXCEPTION);
        }
    }
}
