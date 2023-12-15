package vendingmachine.dto.response;

import java.util.LinkedHashMap;
import java.util.Map;
import vendingmachine.domain.Coin;

public record MachineMoneyResponse(Map<Integer, Integer> machineMoney) {
    public static MachineMoneyResponse from(Map<Coin, Integer> machineMoney) {
        Map<Integer, Integer> machineMoneyResponse = new LinkedHashMap<>();

        for (Coin coin : Coin.values()) {
            int coinType = coin.getAmount();
            machineMoneyResponse.put(coinType, machineMoney.get(coin));
        }

        return new MachineMoneyResponse(machineMoneyResponse);
    }
}
