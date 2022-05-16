package ex01;
import java.util.UUID;

public class Transaction {

    enum Category {
        INCOME, OUTCOME
    }

    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private Integer amount;

    public Transaction(User recipient, User sender, Category transferCategory, Integer amount) {
        if ((transferCategory == Category.OUTCOME && amount < 0 && recipient.getBalance() >= -amount)
                || (transferCategory == Category.INCOME && amount > 0 && sender.getBalance() >= amount)) {
            this.recipient = recipient;
            this.sender = sender;
            this.amount = amount;
            this.transferCategory = transferCategory;
            identifier = UUID.randomUUID();
            System.out.println("Transaction: " + this.identifier + " was created.");
        }
        else
            System.err.println("Transaction can't be created.");
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getAmount() {
        return amount;
    }

}
