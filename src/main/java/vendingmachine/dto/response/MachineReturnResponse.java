package vendingmachine.dto.response;

import java.util.Map;

public record MachineReturnResponse(Map<Integer, Integer> machineReturn) {
    public static MachineReturnResponse from(Map<Integer, Integer> machineReturn) {
        return new MachineReturnResponse(machineReturn);
    }
}
