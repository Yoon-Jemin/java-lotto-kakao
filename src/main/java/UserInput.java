import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInput {

    private Number number;
    private Separator separator;

    public UserInput(String input) {
        this.number = new Number(new ArrayList<>());
        this.separator = new Separator();
        validate(input);
    }

    public void validate(String text) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(1);
            this.separator.addSeparator(customDelimiter);

//            String[] tokens= m.group(2).split(customDelimiter);
        }
    }

    public Number getNumber() {
        return number;
    }

    public Separator getSeparator() {
        return separator;
    }
}
