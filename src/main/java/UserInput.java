import java.util.ArrayList;
import java.util.HashSet;

public class UserInput {

    private Number number;
    private Separator separator;

    public UserInput(String s) {
        this.number = new Number(new ArrayList<>());
        this.separator = new Separator(new HashSet<>());
    }

    public Number getNumber() {
        return number;
    }

    public Separator getSeparator() {
        return separator;
    }
}
