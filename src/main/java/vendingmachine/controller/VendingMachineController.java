package vendingmachine.controller;

import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public VendingMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

    }
}
