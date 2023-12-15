package vendingmachine.view;

import java.util.Map.Entry;
import vendingmachine.dto.response.MachineMoneyResponse;
import vendingmachine.dto.response.MachineReturnResponse;

public class OutputView {
    public void printMachineMoney(MachineMoneyResponse machineMoneyResponse) {
        System.out.println();
        System.out.println("자판기가 보유한 동전");
        for (Entry<Integer, Integer> entry : machineMoneyResponse.machineMoney().entrySet()) {
            int coinType = entry.getKey();
            int coinCount = entry.getValue();
            System.out.printf("%s원 - %d개%n", coinType, coinCount);
        }
    }

    public void printInputMoney(int inputMoney) {
        System.out.println();
        System.out.printf("투입 금액: %d원%n", inputMoney);
    }

    public void printReturnChange(MachineReturnResponse machineReturnResponse) {
        System.out.println("잔돈");
        for (Entry<Integer, Integer> entry : machineReturnResponse.machineReturn().entrySet()) {
            int coinType = entry.getKey();
            int coinCount = entry.getValue();
            System.out.printf("%d원 - %d개%n", coinType, coinCount);
        }
    }
}
