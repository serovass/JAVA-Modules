package ex01;

public class Program {

    public static void main(String[] args) {

        System.out.println("Users:");
        User user1 = new User("Max", 10000);
        User user2 = new User("Tom", 15000);
        User user3 = new User("Sam", 0);
        user3.setBalance(19);

        System.out.println(user1.getIdentifier() + " " + user1.getName() + ": " + user1.getBalance());
        System.out.println(user2.getIdentifier() + " " + user2.getName() + ": " + user2.getBalance());
        System.out.println(user3.getIdentifier() + " " + user3.getName() + ": " + user3.getBalance());

        System.out.println();

        Transaction transaction1 = new Transaction(user3, user1, Transaction.Category.INCOME, 100);
        getTransactionData(transaction1);

        System.out.println();

        Transaction transaction2 = new Transaction(user2, user1, Transaction.Category.OUTCOME, -1000);
        getTransactionData(transaction2);

        System.out.println();

        Transaction transaction3 = new Transaction(user3, user1, Transaction.Category.OUTCOME, -1000);
        getTransactionData(transaction3);
    }

    private static void getTransactionData(Transaction transaction)
    {
        if (transaction.getIdentifier() != null) {
            System.out.println("Recipient: " + transaction.getRecipient().getIdentifier()
                    + " " + transaction.getRecipient().getName()
                    + "\nSender: " + transaction.getSender().getIdentifier()
                    + " " + transaction.getSender().getName()
                    + "\nTransaction amount: " + transaction.getAmount() );
        }
    }
}
