package vendingmachine.dto.request;

import vendingmachine.view.util.InputUtil;

public record InputMoneyRequest(String input) {
    public int toInt() {
        return InputUtil.parseToInt(input);
    }
}
