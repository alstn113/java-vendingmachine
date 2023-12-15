package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MachineController machineController = createMachineController();
        machineController.run();
    }

    private static MachineController createMachineController() {
        return new MachineController(
                new InputView(),
                new OutputView()
        );
    }
}
