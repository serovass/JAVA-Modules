package ex02;

public class Program {

    public static void main(String[] args) {


        User user1 = new User("Max", 10000);
        User user2 = new User("Tom", 15000);
        User user3 = new User("Sam", 0);
        user3.setBalance(19);

        UsersList list = new UsersArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        System.out.println("Users: " + list.getUsersNumber());

        System.out.println(user1.getIdentifier() + " " + user1.getName() + ": " + user1.getBalance());
        System.out.println(user2.getIdentifier() + " " + user2.getName() + ": " + user2.getBalance());
        System.out.println(user3.getIdentifier() + " " + user3.getName() + ": " + user3.getBalance());

        System.out.println();

        Transaction transaction1 = new Transaction(list.getByIndex(2), list.getByIndex(0), Transaction.Category.INCOME, 100);
        printTransactionData(transaction1);

        System.out.println();

        Transaction transaction2 = new Transaction(list.getById(2), list.getById(1), Transaction.Category.OUTCOME, -1000);
        printTransactionData(transaction2);

        System.out.println();

        Transaction transaction3 = new Transaction(user3, user1, Transaction.Category.OUTCOME, -1000);
        printTransactionData(transaction3);
    }

    private static void printTransactionData(Transaction transaction)
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
