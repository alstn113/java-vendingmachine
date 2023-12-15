package vendingmachine.dto.response;

import java.util.Map;

public record MachineMoneyResponse(Map<Integer, Integer> machineMoney) {
    public static MachineMoneyResponse from(Map<Integer, Integer> machineMoney) {
        return new MachineMoneyResponse(machineMoney);
    }
}
