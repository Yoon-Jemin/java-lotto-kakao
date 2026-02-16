package calculator;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private Separator separator;
    private Number number;

    public Parser(String input) {
        this.separator = new Separator(input);
        if (separator.hasCustomSeparator()) {
            input = input.substring(4);
        }
        validate(input);
        String parsedInput = parse(input);
        this.number = new Number(parsedInput);
    }

    private void validate(String input) {
        for (char s : input.toCharArray()) {
            validateString(s);
        }
    }

    private void validateString(char c) {
        if (!Character.isDigit(c) && !this.separator.contains(String.valueOf(c))) {
            throw new IllegalArgumentException("등록되지 않은 커스텀 구분자가 입력되었습니다.");
        }
    }

    public String parse(String input) {
        String parsedInput = parseSeparator(input);
        validateConsecutiveDelimiters(parsedInput);
        return parsedInput;
    }

    private String parseSeparator(String input) {
        String regex = separator.getSeparators().stream()
                .map(Pattern::quote)
                .collect(Collectors.joining("", "[", "]"));

        return input.replaceAll(regex, " ");
    }

    private void validateConsecutiveDelimiters(String input) {
        if (input.contains("  ")) {
            throw new IllegalArgumentException("구분자는 연속적으로 사용할 수 없습니다.");
        }
    }

    public Separator getSeparator() {
        return separator;
    }

    public Number getNumber() {
        return number;
    }
}
