package vendingmachine.model;

public class Menu {
    private final String name;
    private final int price;
    private int count;

    Menu(String name, String price, String count) {
        isValidInput(name, price, count);
        this.name = name;
        this.price = Integer.parseInt(price);
        this.count = Integer.parseInt(count);
    }

    private void isValidInput(String name, String price, String count) {
        isValidName(name);
        isValidPrice(price);
        isValidCount(count);
    }

    private void isValidName(String name) {
        if (name.chars().allMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("[ERROR] 상품 이름은 공백이면 안됩니다");
        }
    }

    private void isValidPrice(String price) {
        isValidNumber(price);
        if (Integer.parseInt(price) < 100 || Integer.parseInt(price) % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 100원부터 시작하며 10원으로 나누어 떨어져야 합니다.");
        }
    }

    private void isValidCount(String count) {
        isValidNumber(count);
        if (Integer.parseInt(count) < 1) {
            throw new IllegalArgumentException("[ERROR] 상품 수량은 최소 1개 이상이어야 합니다");
        }
    }

    private void isValidNumber(String number) {
        if (!number.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 입력값은 숫자로만 구성되어야 합니다.");
        }
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int decreaseMenuCount() {
        return this.count--;
    }
}
