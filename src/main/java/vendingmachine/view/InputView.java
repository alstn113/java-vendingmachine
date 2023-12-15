package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.request.MachineMoneyRequest;

public class InputView {
    public MachineMoneyRequest readMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new MachineMoneyRequest(input);
    }
}
