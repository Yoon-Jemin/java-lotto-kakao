package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

public interface OutputView {

    public void printMessage(String message);

    public void printLog(List<Integer> list);

}
