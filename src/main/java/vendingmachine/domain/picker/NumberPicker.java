package vendingmachine.domain.picker;

import java.util.List;

@FunctionalInterface
public interface NumberPicker {
    int pick(List<Integer> numbers);
}
