package vendingmachine.dto.request;

import vendingmachine.domain.Machine;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;
import vendingmachine.view.util.InputUtil;

public record ProductToBuyRequest(String input) {
    public String toProduct(Machine machine) {
        InputUtil.validateInputNotEmpty(input);
        if (!machine.isExistProduct(input)) {
            throw new InvalidInputException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
        return input;
    }
}
