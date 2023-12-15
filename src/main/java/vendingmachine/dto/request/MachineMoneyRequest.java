package vendingmachine.dto.request;

import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;
import vendingmachine.view.util.InputUtil;

public record MachineMoneyRequest(String input) {
    public int toInt() {
        InputUtil.validateInputNotEmpty(input);
        int money = InputUtil.parseToInt(input);
        if (money < 0) {
            throw new InvalidInputException(ErrorMessage.INVALID_MONEY);
        }
        return money;
    }
}
