package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new VendingMachineController(
                new InputView(),
                new OutputView()
        ).run();
    }
}
