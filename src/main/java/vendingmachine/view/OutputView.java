package vendingmachine.view;

import java.util.Map;
import vendingmachine.dto.response.ChangeCoinsResponse;
import vendingmachine.dto.response.MachineCoinsResponse;

public class OutputView {
    private static final String COIN_FORMAT = "%d원 - %d개%n";

    public void printMachineCoins(MachineCoinsResponse machineCoinsResponse) {
        System.out.println();
        System.out.println("자판기가 보유한 동전");
        Map<Integer, Integer> machineCoins = machineCoinsResponse.coins();
        machineCoins.forEach((coin, count) -> System.out.printf(COIN_FORMAT, coin, count));
    }

    public void printInputMoney(int inputMoney) {
        System.out.println();
        System.out.printf("투입 금액: %d원%n", inputMoney);
    }

    public void printChangeCoins(ChangeCoinsResponse changeCoinsResponse) {
        System.out.println("잔돈");
        Map<Integer, Integer> changeCoins = changeCoinsResponse.changeCoins();
        changeCoins.forEach((coin, count) -> System.out.printf(COIN_FORMAT, coin, count));
    }
}
