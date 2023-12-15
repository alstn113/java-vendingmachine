package vendingmachine.view;

import java.util.Map.Entry;
import vendingmachine.dto.response.MachineMoneyResponse;

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
}
