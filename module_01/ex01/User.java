package ex01;

public class User {

    private final Integer identifier;
    private final String name;
    private Integer balance;

    public User(String name, Integer balance) {

        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

    public Integer getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public void setBalance(Integer balance) {

        if (balance > 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
    }

}
