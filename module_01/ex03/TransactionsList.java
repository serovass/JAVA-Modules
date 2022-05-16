package ex03;

public interface TransactionsList {

    void add(Transaction transaction);
    Transaction remove(String uuid);
    Transaction[] toArray();
}
