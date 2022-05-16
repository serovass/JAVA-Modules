package ex03;

class TransactionNotFoundException extends RuntimeException {

}

public class TransactionsLinkedList implements TransactionsList {
    private Node start;
    private Node end;
    private Integer transactionsNumber;

    public TransactionsLinkedList() {

        start = new Node(null);
        end = new Node(null);
        start.next = end;
        end.prev = start;
        transactionsNumber = 0;
    }

    @Override
    public void add(Transaction transaction) {

        Node newNode = new Node(transaction);
        newNode.insertBefore(end);
        transactionsNumber++;
    }

    @Override
    public Transaction remove(String uuid) {

        Node node = first();
        while (node != end) {
            if (uuid.equals(node.getTransaction().getIdentifier().toString())) {
                node.cut();
                transactionsNumber--;
                return node.getTransaction();
            }
            node = node.getNext();
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[transactionsNumber];

        Node node = first();
        for (int i = 0; i < transactionsNumber; i++, node = node.getNext()) {
            transactions[i] = node.getTransaction();
        }
        return transactions;
    }

    private Node first() {

        return start.getNext();
    }

    //-----------------------------------
    private class Node {

        private final Transaction element;
        private Node next;
        private Node prev;

        public Node(Transaction element) {

            this.element = element;
        }

        public Node getNext() {

            return next;
        }

        public void insertBefore(Node beforeNode) {

            prev = beforeNode.prev;
            prev.next = this;
            beforeNode.prev = this;
            next = beforeNode;
        }

        public void cut() {
            next.prev = prev;
            prev.next = next;
            next = null;
            prev = null;
        }

        public Transaction getTransaction() {
            return element;
        }


    }
    //-----------------------------------

}
