package vendingmachine.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import vendingmachine.domain.picker.NumberPicker;
import vendingmachine.domain.picker.RandomNumberPicker;

public class Machine {
    private final Map<Coin, Integer> coins = new EnumMap<>(Coin.class);
    private final NumberPicker numberPicker;

    public Machine(int money, NumberPicker numberPicker) {
        this.numberPicker = numberPicker;
        initCoins();
        generateCoins(money);
    }

    public Machine(int money) {
        this(money, new RandomNumberPicker());
    }

    private void initCoins() {
        Arrays.stream(Coin.values())
                .forEach(coin -> coins.put(coin, 0));
    }

    private void generateCoins(int money) {
        int coinTypeCount = Coin.values().length;
        List<Integer> numbers = IntStream.range(0, coinTypeCount)
                .boxed()
                .toList();

        while (money > 0) {
            int randomIndex = numberPicker.pick(numbers);
            Coin coin = Coin.values()[randomIndex];
            int coinValue = coin.getAmount();

            if (money < coinValue) {
                continue;
            }

            money -= coinValue;
            coins.put(coin, coins.get(coin) + 1);
        }
    }
}
