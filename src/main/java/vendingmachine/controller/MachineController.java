package vendingmachine.controller;


import vendingmachine.domain.Machine;
import vendingmachine.dto.request.MachineMoneyRequest;
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
    }

    private int readMachineMoney() {
        return InputUtil.retryOnException(() -> {
            MachineMoneyRequest dto = inputView.readMachineMoney();
            return dto.toInt();
        });
    }
}
