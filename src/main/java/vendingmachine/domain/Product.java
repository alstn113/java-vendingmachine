package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;

public record Product(String name, int price, int quantity) {
    private static final int MIN_QUANTITY = 0;
    private static final int MIN_PRICE = 100;
    private static final int PRICE_UNIT = 10;

    public Product {
        validateQuantity(quantity);
        validatePrice(price);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new InvalidInputException(ErrorMessage.INVALID_QUANTITY);
        }
    }

    private void validatePrice(int price) {
        if (price < MIN_PRICE) {
            throw new InvalidInputException(ErrorMessage.INVALID_PRICE);
        }

        if (price % PRICE_UNIT != 0) {
            throw new InvalidInputException(ErrorMessage.INVALID_PRICE_UNIT);
        }
    }

}
