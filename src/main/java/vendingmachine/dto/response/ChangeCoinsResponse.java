package vendingmachine.dto.response;

import java.util.LinkedHashMap;
import java.util.Map;
import vendingmachine.domain.Coin;

public record ChangeCoinsResponse(Map<Integer, Integer> changeCoins) {
    public static ChangeCoinsResponse from(Map<Coin, Integer> changeCoins) {
        Map<Integer, Integer> coinCount = new LinkedHashMap<>();
        changeCoins.forEach((coin, count) -> coinCount.put(coin.getAmount(), count));

        return new ChangeCoinsResponse(coinCount);
    }
}
