package vendingmachine.controller;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.Machine;
import vendingmachine.dto.request.InputMoneyRequest;
import vendingmachine.dto.request.ItemNameToBuyRequest;
import vendingmachine.dto.request.ItemsRequest;
import vendingmachine.dto.request.MachineMoneyRequest;
import vendingmachine.dto.response.ChangeCoinsResponse;
import vendingmachine.dto.response.MachineCoinsResponse;
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
        printMachineCoins(machine.getCoins());

        Map<Item, Integer> items = readItems();
        machine.setItems(items);

        int inputMoney = readInputMoney();

        while (inputMoney > 0) {
            if (machine.allItemsSoldOut() || inputMoney < machine.getMinimumPrice()) {
                break;
            }

            printInputMoney(inputMoney);
            Item itemNameToBuy = readItemNameToBuy(machine);
            int price = machine.buyItem(itemNameToBuy);
            inputMoney -= price;
        }

        Map<Coin, Integer> changeCoins = machine.returnChangeCoins(inputMoney);
        printInputMoney(inputMoney);
        printChangeCoins(ChangeCoinsResponse.from(changeCoins));
    }

    private Machine readMachineMoney() {
        return InputUtil.retryOnException(() -> {
            MachineMoneyRequest dto = inputView.readMachineMoney();
            int money = dto.toInt();
            return new Machine(money);
        });
    }

    private Map<Item, Integer> readItems() {
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

    private Item readItemNameToBuy(Machine machine) {
        return InputUtil.retryOnException(() -> {
            ItemNameToBuyRequest dto = inputView.readItemNameToBuy();
            return machine.getItemOrThrow(dto.toString());
        });
    }

    private void printMachineCoins(Map<Coin, Integer> coins) {
        MachineCoinsResponse dto = MachineCoinsResponse.from(coins);
        outputView.printMachineCoins(dto);
    }

    private void printInputMoney(int inputMoney) {
        outputView.printInputMoney(inputMoney);
    }

    private void printChangeCoins(ChangeCoinsResponse dto) {
        outputView.printChangeCoins(dto);
    }
}
