package ex03;

public class Program {

    public static void main(String[] args) {

        User user1 = new User("Max", 10000);
        User user2 = new User("Tom", 15000);
        User user3 = new User("Sam", 0);
        user3.setBalance(19);

        UsersList userList = new UsersArrayList();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        TransactionsList transactionsList = new TransactionsLinkedList();
        user1.setTransactionsList(transactionsList);
        user2.setTransactionsList(transactionsList);
        user3.setTransactionsList(transactionsList);

        System.out.println("Users: " + userList.getUsersNumber());

        System.out.println(user1.getIdentifier() + " " + user1.getName() + ": " + user1.getBalance());
        System.out.println(user2.getIdentifier() + " " + user2.getName() + ": " + user2.getBalance());
        System.out.println(user3.getIdentifier() + " " + user3.getName() + ": " + user3.getBalance());

        System.out.println();
        Transaction transaction1 = new Transaction(userList.getByIndex(2), userList.getByIndex(0), Transaction.Category.INCOME, 100);
        Transaction transaction2 = new Transaction(userList.getById(2), userList.getById(1), Transaction.Category.OUTCOME, -1000);
        Transaction transaction3 = new Transaction(user3, user1, Transaction.Category.OUTCOME, -10);


        transactionsList.add(transaction1);
        transactionsList.add(transaction2);
        transactionsList.add(transaction3);

        for (Transaction i : transactionsList.toArray()) {
            System.out.println();
            printTransactionData(i);
        }

        System.out.println("---------------------------------------------------");

        transactionsList.remove(transaction3.getIdentifier().toString());
        for (Transaction i : transactionsList.toArray()) {
            System.out.println();
            printTransactionData(i);
        }

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
