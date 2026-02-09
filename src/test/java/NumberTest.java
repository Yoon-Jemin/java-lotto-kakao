import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @Test
    void numberTest() {
        UserInput input = new UserInput("1,2,3");
        Number number = input.getNumber();
        Separator separator = input.getSeparator();

        Assertions.assertThat(number.getNumbers()).containsExactly(1,2,3);
        Assertions.assertThat(separator.getSeparators()).contains(",");
    }

}