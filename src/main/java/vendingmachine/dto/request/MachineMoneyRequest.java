package vendingmachine.dto.request;

import vendingmachine.view.util.InputUtil;

public record MachineMoneyRequest(String input) {
    public int toInt() {
        InputUtil.validateInputNotEmpty(input);
        return InputUtil.parseToInt(input);
    }
}
