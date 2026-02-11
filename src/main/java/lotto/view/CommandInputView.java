package lotto.view;

import java.util.Scanner;

public class CommandInputView implements InputView {
    private Scanner scanner;

    public CommandInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String input() { return scanner.nextLine(); }

    @Override
    public String inputPrice() {
        try {
            String input = scanner.nextLine();
            Integer.parseInt(input);
            return input;
        } catch (Exception e) {
            throw new IllegalArgumentException("가격 정보가 숫자 형식이 아닙니다.");
        }
    }
}
