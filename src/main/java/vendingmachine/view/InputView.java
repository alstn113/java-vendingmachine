package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.request.InputMoneyRequest;
import vendingmachine.dto.request.MachineMoneyRequest;
import vendingmachine.dto.request.ProductToBuyRequest;
import vendingmachine.dto.request.ProductsRequest;

public class InputView {
    public MachineMoneyRequest readMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new MachineMoneyRequest(input);
    }

    public ProductsRequest readProducts() {
        System.out.println();
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = Console.readLine();
        return new ProductsRequest(input);
    }

    public InputMoneyRequest readInputMoney() {
        System.out.println();
        System.out.println("투입 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new InputMoneyRequest(input);
    }

    public ProductToBuyRequest readProductToBuy() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        String input = Console.readLine();
        return new ProductToBuyRequest(input);
    }
}
