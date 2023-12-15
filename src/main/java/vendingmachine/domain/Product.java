package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;

public class Product {

    private static final int MIN_QUANTITY = 0;
    private static final int MIN_PRICE = 100;
    private static final int PRICE_UNIT = 10;
    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public boolean isSoldOut() {
        return quantity == 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void sell() {
        quantity--;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
