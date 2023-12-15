package vendingmachine.exception;

public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    INPUT_NOT_EMPTY("입력값은 비어있으면 안됩니다."),
    INVALID_MONEY("금액은 음수일 수 없습니다."),
    INVALID_QUANTITY("수량은 음수일 수 없습니다."),
    INVALID_PRICE("가격은 100원 이상이어야 합니다."),
    INVALID_PRICE_UNIT("가격은 10원 단위여야 합니다."),
    INVALID_PRODUCT_FORMAT("상품 형식이 올바르지 않습니다."),
    INVALID_INPUT_MONEY("금액은 음수일 수 없습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
