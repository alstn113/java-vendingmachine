package vendingmachine.controller;


import java.util.Map;
import vendingmachine.domain.Machine;
import vendingmachine.domain.Product;
import vendingmachine.dto.request.InputMoneyRequest;
import vendingmachine.dto.request.MachineMoneyRequest;
import vendingmachine.dto.request.ProductToBuyRequest;
import vendingmachine.dto.request.ProductsRequest;
import vendingmachine.dto.response.MachineMoneyResponse;
import vendingmachine.dto.response.MachineReturnResponse;
import vendingmachine.exception.ErrorMessage;
import vendingmachine.exception.InvalidInputException;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.util.InputUtil;

public class MachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Machine machine = new Machine();

        int money = readMachineMoney();
        machine.putMoney(money);

        outputView.printMachineMoney(MachineMoneyResponse.from(machine.getCoins()));

        Map<String, Product> products = products();
        machine.putProducts(products);

        int inputMoney = readInputMoney();

        while (machine.canBuy(inputMoney)) {
            outputView.printInputMoney(inputMoney);
            String productToBuy = readProductToBuy(machine, inputMoney);
            inputMoney -= machine.buy(productToBuy);
        }

        outputView.printInputMoney(inputMoney);
        outputView.printReturnChange(MachineReturnResponse.from(machine.returnChange(inputMoney)));
    }

    private int readMachineMoney() {
        return InputUtil.retryOnException(() -> {
            MachineMoneyRequest dto = inputView.readMachineMoney();
            return dto.toInt();
        });
    }

    private Map<String, Product> products() {
        return InputUtil.retryOnException(() -> {
            ProductsRequest dto = inputView.readProducts();
            return dto.toProducts();
        });
    }

    private int readInputMoney() {
        return InputUtil.retryOnException(() -> {
            InputMoneyRequest dto = inputView.readInputMoney();
            return dto.toInt();
        });
    }

    private String readProductToBuy(Machine machine, int inputMoney) {
        return InputUtil.retryOnException(() -> {
            ProductToBuyRequest dto = inputView.readProductToBuy();
            String product = dto.toProduct(machine);
            if (machine.getProductPrice(product) > inputMoney) {
                throw new InvalidInputException(ErrorMessage.NOT_ENOUGH_MONEY);
            }
            return product;
        });
    }
}
