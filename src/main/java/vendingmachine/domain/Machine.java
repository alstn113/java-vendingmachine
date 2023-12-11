package vendingmachine.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import vendingmachine.domain.picker.NumberPicker;
import vendingmachine.domain.picker.RandomNumberPicker;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;

public class Machine {
    private final Map<Coin, Integer> coins = new EnumMap<>(Coin.class);
    private final NumberPicker numberPicker;
    private final Map<Item, Integer> items = new HashMap<>();

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

    public void setItems(Map<Item, Integer> items) {
        this.items.putAll(items);
    }

    public int getMinimumPrice() {
        return items.keySet().stream()
                .mapToInt(Item::price)
                .filter(price -> price > 0)
                .min()
                .orElseThrow(() -> new InvalidInputException(ErrorMessage.ITEMS_NOT_FOUND));
    }

    public boolean allItemsSoldOut() {
        return items.values().stream()
                .allMatch(count -> count == 0);
    }

    public Item getItemOrThrow(String itemName) {
        Item item = items.keySet().stream()
                .filter(i -> i.name().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorMessage.ITEM_NOT_EXISTS));

        if (items.get(item) == 0) {
            throw new InvalidInputException(ErrorMessage.ITEM_SOLD_OUT);
        }

        return item;
    }

    public int buyItem(Item item) {
        if (items.get(item) == 0) {
            throw new InvalidInputException(ErrorMessage.ITEM_SOLD_OUT);
        }

        items.put(item, items.get(item) - 1);

        return item.price();
    }

    public Map<Coin, Integer> returnChangeCoins(int inputMoney) {
        Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            int coinAmount = coin.getAmount();
            int coinCount = inputMoney / coinAmount;
            int coinCountInMachine = coins.get(coin);

            if (coinCountInMachine == 0) {
                continue;
            }

            int changeCoinCount = Math.min(coinCount, coinCountInMachine);
            changeCoins.put(coin, changeCoinCount);
            inputMoney -= coinAmount * changeCoinCount;
        }
        return changeCoins;
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }
}
