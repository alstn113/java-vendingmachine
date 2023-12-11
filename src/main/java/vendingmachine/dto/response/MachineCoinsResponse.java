package vendingmachine.dto.response;

import java.util.LinkedHashMap;
import java.util.Map;
import vendingmachine.domain.Coin;

public record MachineCoinsResponse(Map<Integer, Integer> coins) {
    public static MachineCoinsResponse from(Map<Coin, Integer> coins) {
        Map<Integer, Integer> coinCount = new LinkedHashMap<>();

        coins.forEach((coin, count) -> coinCount.put(coin.getAmount(), count));

        return new MachineCoinsResponse(coinCount);
    }
}
