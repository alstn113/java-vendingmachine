package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Machine {
    private final Map<Integer, Integer> coins = new LinkedHashMap<>();
    private final Map<String, Product> products = new HashMap<>();

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

    public boolean isExistProduct(String name) {
        if (products.containsKey(name)) {
            return products.get(name).getQuantity() > 0;
        }
        return false;
    }

    public void putProducts(Map<String, Product> products) {
        this.products.putAll(products);
    }

    public boolean canBuy(int inputMoney) {
        return !allProductsSoldOut() && inputMoney >= findMinPrice();
    }

    private boolean allProductsSoldOut() {
        return products.values().stream().allMatch(Product::isSoldOut);
    }

    private int findMinPrice() {
        return products.values().stream()
                .filter(product -> !product.isSoldOut())
                .mapToInt(Product::getPrice)
                .min()
                .orElse(0);
    }

    public int buy(String name) {
        Product product = products.get(name);
        product.sell();
        return product.getPrice();
    }

    public int getProductPrice(String name) {
        return products.get(name).getPrice();
    }

    public Map<Integer, Integer> getCoins() {
        return Collections.unmodifiableMap(coins);
    }

    public Map<Integer, Integer> returnChange(int inputMoney) {
        Map<Integer, Integer> change = new LinkedHashMap<>();

        for (Entry<Integer, Integer> coin : coins.entrySet()) {
            int coinType = coin.getKey();
            int coinCount = coin.getValue();
            int changeCount = inputMoney / coinType;

            if (changeCount > coinCount) {
                changeCount = coinCount;
            }

            change.put(coinType, changeCount);
            inputMoney -= coinType * changeCount;
        }

        return change;
    }
}
