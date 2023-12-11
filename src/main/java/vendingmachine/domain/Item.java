package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;

public record Item(String name, int price) {
    private static final int MINIMUM_PRICE = 100;
    private static final int PRICE_DIVISIBLE_BY = 10;

    public Item {
        validateName(name);
        validatePriceOverMinimum(price);
        validatePriceDivisibleBy(price);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException(ErrorMessage.NAME_IS_EMPTY);
        }
    }

    private void validatePriceOverMinimum(int price) {
        if (price < MINIMUM_PRICE) {
            throw new InvalidInputException(ErrorMessage.PRICE_IS_UNDER_MINIMUM);
        }
    }

    private void validatePriceDivisibleBy(int price) {
        if (price % PRICE_DIVISIBLE_BY != 0) {
            throw new InvalidInputException(ErrorMessage.PRICE_IS_NOT_DIVISIBLE_BY);
        }
    }
}
