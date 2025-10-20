package calculator.constant;

public enum ErrorMessage {
    EMPTY_INPUT("[ERROR] 입력값이 비어있습니다."),
    NOT_POSITIVE_INPUT("[ERROR] 입력값은 양수이어야 합니다."),
    INVALID_INPUT("[ERROR] 잘못된 형식입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
