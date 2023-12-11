package vendingmachine.controller;

import java.util.List;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.dto.request.InputMoneyRequest;
import vendingmachine.dto.request.ItemNameToBuyRequest;
import vendingmachine.dto.request.ItemsRequest;
import vendingmachine.dto.request.MachineMoneyRequest;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;
import vendingmachine.view.util.InputUtil;

public class VendingMachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Machine machine = readMachineMoney();
        List<Item> items = readItems();
        int inputMoney = readInputMoney();
        String itemNameToBuy = readItemNameToBuy();
    }

    private Machine readMachineMoney() {
        return InputUtil.retryOnException(() -> {
            MachineMoneyRequest dto = inputView.readMachineMoney();
            int money = dto.toInt();
            return new Machine(money);
        });
    }

    private List<Item> readItems() {
        return InputUtil.retryOnException(() -> {
            ItemsRequest dto = inputView.readItems();
            return dto.toItems();
        });
    }

    private int readInputMoney() {
        return InputUtil.retryOnException(() -> {
            InputMoneyRequest dto = inputView.readInputMoney();
            return dto.toInt();
        });
    }

    private String readItemNameToBuy() {
        return InputUtil.retryOnException(() -> {
            ItemNameToBuyRequest dto = inputView.readItemNameToBuy();
            return dto.toString();
        });
    }
}
