package lotto.domain;

import java.util.Objects;

public class LottoNumber {

    public static final String NUMBER_OUT_OF_RANGE_EXCEPTION = "1 ~ 45 범위를 벗어나는 숫자가 입력되었습니다.";

    private int number;

    public LottoNumber(int number) {
        validateRange(number);
        this.number = number;
    }

    private void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE_EXCEPTION);
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
