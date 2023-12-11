package vendingmachine.domain.picker;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberPicker implements NumberPicker {
    @Override
    public int pick(List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }
}
