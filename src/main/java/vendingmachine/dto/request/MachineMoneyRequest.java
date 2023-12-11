package vendingmachine.dto.request;

import vendingmachine.view.util.InputUtil;

public record MachineMoneyRequest(String input) {
    public int toInt() {
        return InputUtil.parseToInt(input);
    }
}
