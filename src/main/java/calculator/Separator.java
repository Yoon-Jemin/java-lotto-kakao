package calculator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Separator {

    private boolean hasCustomSeparator;
    private Set<String> separators;

    public Separator(String input) {
        this.hasCustomSeparator = false;
        this.separators = new HashSet<>(List.of(",", ":"));
        validate(input);
    }

    public void validate(String text) {
        validateCustomSeparator(text);
        if (hasCustomSeparator) validateFormat(text);
    }

    private void validateCustomSeparator(String text) {
        hasCustomSeparator = !text.matches("^[0-9].*");
    }

    private void validateFormat(String text) {
        Matcher m = Pattern.compile("^//(.)\n(.*)").matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(1);
            validateCustomDelimiter(customDelimiter);
            this.separators.add(customDelimiter);
            return;
        }

        throw new IllegalArgumentException("커스텀 구분자 추가 형식이 잘못되었습니다.");
    }

    private void validateCustomDelimiter(String customDelimiter) {
        char ch = customDelimiter.toCharArray()[0];
        if (Character.isDigit(ch)) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자가 될 수 없습니다.");
        }
    }


    public boolean contains(String input) {
        return separators.contains(input);
    }

    public boolean hasCustomSeparator() {
        return hasCustomSeparator;
    }

    public Set<String> getSeparators() {
        return separators;
    }
}
