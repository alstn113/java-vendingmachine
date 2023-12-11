package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ItemTest {
    @ParameterizedTest
    @ValueSource(ints = {-10, 10, 50, 99})
    void 금액이_100원보다_작을_수_없다(int price) {
        assertThatThrownBy(() -> new Item("콜라", price))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 150, 200, 1000})
    void 금액이_100원보다_크다(int price) {
        Item item = new Item("콜라", price);
        assertThat(item.name()).isEqualTo("콜라");
        assertThat(item.price()).isEqualTo(price);
    }

    @ParameterizedTest
    @ValueSource(ints = {101, 158, 201, 1001})
    void 금액이_10원_단위로_나누어_떨어지지_않으면_안된다(int price) {
        assertThatThrownBy(() -> new Item("콜라", price))
                .isInstanceOf(IllegalArgumentException.class);
    }
}