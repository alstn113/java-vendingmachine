package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Machine {
    private final Map<Integer, Integer> coins = new LinkedHashMap<>();

    public Machine() {
        initMachine();
    }

    private void initMachine() {
        for (Coin coin : Coin.values()) {
            coins.put(coin.getAmount(), 0);
        }
    }

    public void putMoney(int money) {
        generateRandomCoin(money);
    }

    private void generateRandomCoin(int money) {
        List<Integer> coinTypes = Arrays.stream(Coin.values())
                .map(Coin::getAmount)
                .toList();

        while (money > 0) {
            int coin = Randoms.pickNumberInList(coinTypes);

            if (money >= coin) {
                money -= coin;
                coins.put(coin, coins.get(coin) + 1);
            }
        }
    }

    public Map<Integer, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
