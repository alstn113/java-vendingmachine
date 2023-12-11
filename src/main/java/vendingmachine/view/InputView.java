package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.request.InputMoneyRequest;
import vendingmachine.dto.request.ItemNameToBuyRequest;
import vendingmachine.dto.request.ItemsRequest;
import vendingmachine.dto.request.MachineMoneyRequest;

public class InputView {
    public MachineMoneyRequest readMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new MachineMoneyRequest(input);
    }

    public ItemsRequest readItems() {
        System.out.println();
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = Console.readLine();
        return new ItemsRequest(input);
    }

    public InputMoneyRequest readInputMoney() {
        System.out.println();
        System.out.println("투입 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new InputMoneyRequest(input);
    }

    public ItemNameToBuyRequest readItemNameToBuy() {
        System.out.println();
        System.out.println("구매할 상품명을 입력해 주세요.");
        String input = Console.readLine();
        return new ItemNameToBuyRequest(input);
    }
}
