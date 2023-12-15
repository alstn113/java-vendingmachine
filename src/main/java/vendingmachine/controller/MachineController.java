package vendingmachine.controller;


import java.util.Map;
import vendingmachine.domain.Machine;
import vendingmachine.domain.Product;
import vendingmachine.dto.request.MachineMoneyRequest;
import vendingmachine.dto.request.ProductsRequest;
import vendingmachine.dto.response.MachineMoneyResponse;
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
}
