package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Machine {
    private final Map<Coin, Integer> coins = new LinkedHashMap<>();

    public Machine(int money) {
        initMachine();
        generateRandomCoin(money);
    }

    private void initMachine() {
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
    }

    private void generateRandomCoin(int money) {
        while (money > 0) {
            List<Coin> coinList = Arrays.stream(Coin.values()).toList();
            List<Integer> numbers = IntStream.range(0, coinList.size()).boxed().toList();
            int num = Randoms.pickNumberInList(numbers);
            Coin coin = coinList.get(num);

            if (money >= coin.getAmount()) {
                money -= coin.getAmount();
                coins.put(coin, coins.get(coin) + 1);
            }
        }
    }

    public Map<Coin, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }
}
