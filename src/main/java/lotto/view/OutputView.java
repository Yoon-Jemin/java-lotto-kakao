package lotto.view;

import lotto.LottoNumber;
import lotto.LottoStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface OutputView {

    void printLog(List<Integer> list);

    void printPriceMessage();

    void printLottoCountMessage(int lottoCount);

    void printStatistics(Map<LottoStatus, Integer> statuses);

    void printProfitRate(double profitRate);

    void printWinningLottoMessage();

    void printBonusNumberMessage();
}
