package vendingmachine.dto.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vendingmachine.domain.Item;
import vendingmachine.view.util.InputUtil;

public record ItemsRequest(String input) {
    private static final String COMMA = ",";
    private static final String SEMI_COLON = ";";

    public List<Item> toItems() {
        List<Item> items = new ArrayList<>();
        String[] inputs = splitInput(input, SEMI_COLON);
        Arrays.stream(inputs)
                .forEach(item -> {
                    String removedBracket = input.substring(1, input.length() - 1);
                    String[] itemInfo = splitInput(removedBracket, COMMA);
                    Item newItem = new Item(itemInfo[0],
                            InputUtil.parseToInt(itemInfo[1]),
                            InputUtil.parseToInt(itemInfo[2])
                    );
                    items.add(newItem);
                });

        return items;
    }

    private String[] splitInput(String input, String delimiter) {
        return input.split(delimiter, -1);
    }
}
