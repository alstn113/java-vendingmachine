package vendingmachine.exception;


public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    PRICE_IS_UNDER_MINIMUM("상품의 가격은 100원 이상이어야 합니다."),
    PRICE_IS_NOT_DIVISIBLE_BY("상품의 가격은 10원 단위로 나누어져야 합니다."),
    NAME_IS_EMPTY("상품의 이름은 비어있을 수 없습니다."),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
