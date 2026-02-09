package calculator;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private String before;
    private String after;
    private Separator separator;
    private Number number;

    public Parser(String input) {
        this.before = input;
        this.after = before;
        this.separator = new Separator(input);
        validate();
        parse();
        this.number = new Number(after);
    }

    private void validate() {
        for (char s : after.toCharArray()) {
            validateString(s);
        }
    }

    private void validateString(char c) {
        if (!Character.isDigit(c) && !this.separator.contains(String.valueOf(c))) {
            throw new IllegalArgumentException("등록되지 않은 커스텀 구분자가 입력되었습니다.");
        }
    }

    public void parse() {
        parseSeparator();
        validateConsecutiveDelimiters();
    }

    private void validateConsecutiveDelimiters() {
        if (after.contains("  ")) {
            throw new IllegalArgumentException("구분자는 연속적으로 사용할 수 없습니다.");
        }
    }

    private void parseSeparator() {
        if (separator.hasCustomSeparator()) {
            after = before.substring(5);
        }

        String regex = separator.getSeparators().stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("", "[", "]"));

        after = after.replaceAll(regex, " ");
    }

    public Separator getSeparator() {
        return separator;
    }

    public Number getNumber() {
        return number;
    }
}
