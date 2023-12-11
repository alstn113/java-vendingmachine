package vendingmachine.dto.request;

public record ItemNameToBuyRequest(String input) {
    public String toString() {
        return input;
    }
}
