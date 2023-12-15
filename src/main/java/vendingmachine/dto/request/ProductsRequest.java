package vendingmachine.dto.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.domain.Product;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;
import vendingmachine.view.util.InputUtil;

public record ProductsRequest(String input) {
    private static final String SEMICOLON = ";";
    private static final String COMMA = ",";


    public Map<String, Product> toProducts() {
        Map<String, Product> products = new HashMap<>();

        try {
            InputUtil.validateInputNotEmpty(input);
            List<String> productFormats = InputUtil.parseToList(input, SEMICOLON);

            for (String productFormat : productFormats) {
                productFormat = productFormat.substring(1, productFormat.length() - 1);
                List<String> productInfo = InputUtil.parseToList(productFormat, COMMA);
                if (productInfo.size() != 3) {
                    throw new InvalidInputException(ErrorMessage.INVALID_PRODUCT_FORMAT);
                }

                String name = productInfo.get(0);
                int price = InputUtil.parseToInt(productInfo.get(1));
                int quantity = InputUtil.parseToInt(productInfo.get(2));

                Product product = new Product(name, price, quantity);
                products.put(name, product);
            }

            return products;
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_PRODUCT_FORMAT);
        }
    }
}
