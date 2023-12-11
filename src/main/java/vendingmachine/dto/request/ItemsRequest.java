package vendingmachine.dto.request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import vendingmachine.domain.Item;
import vendingmachine.view.util.InputUtil;

public record ItemsRequest(String input) {
    private static final String COMMA = ",";
    private static final String SEMI_COLON = ";";

    public Map<Item, Integer> toItems() {
        Map<Item, Integer> items = new HashMap<>();

        String[] inputs = splitInput(input, SEMI_COLON);
        Arrays.stream(inputs)
                .forEach(item -> {
                    String removedBracket = item.substring(1, item.length() - 1);
                    String[] itemInfo = splitInput(removedBracket, COMMA);
                    Item newItem = new Item(itemInfo[0], InputUtil.parseToInt(itemInfo[1]));
                    items.put(newItem, InputUtil.parseToInt(itemInfo[2]));
                });

        return items;
    }

    private String[] splitInput(String input, String delimiter) {
        return input.split(delimiter, -1);
    }
}
