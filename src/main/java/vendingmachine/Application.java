package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.view.console.ConsoleInputView;
import vendingmachine.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        new VendingMachineController(
                new ConsoleInputView(),
                new ConsoleOutputView()
        ).run();
    }
}
