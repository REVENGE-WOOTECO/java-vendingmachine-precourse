package vendingmachine.model;

public class InsertedMoney {
    private int insertedMoney;

    InsertedMoney(String money) {
        isValidMoney(money);
        this.insertedMoney = Integer.parseInt(money);
    }

    private void isValidMoney(String money) {
        if (!money.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 양수여야 합니다");
        }
    }

    public void buyMenu(Menu menu) {
        this.insertedMoney -= menu.getPrice();
    }

    public int getInsertedMoney() {
        return this.insertedMoney;
    }

    public void calculateChange(int totalAmount) {
        this.insertedMoney -= totalAmount;
    }
}
