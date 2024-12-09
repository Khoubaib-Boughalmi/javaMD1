package ex05;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction head;
    private Transaction tail;
    private int         listSize;

    public TransactionsLinkedList() {
        this.head = null;
        this.listSize = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }
        
        if (head == null) {
            head = transaction;
            tail = transaction;
        } else {
            tail.setNextTransaction(transaction);
            tail = transaction;
        }
        
        listSize += 1;
    }

    @Override
    public Transaction removeTransactionById(String id) {
        Transaction previous = null;
        Transaction current = head;
        
        while (current != null) {
            if (current.getIdentifier().equals(id)) {
                if (current == head) {
                    head = current.getNextTransaction();
                } else {
                    previous.setNextTransaction(current.getNextTransaction());
                }
                this.listSize -= 1;
                return current;
            }
            
            previous = current;
            current = current.getNextTransaction();
        }
        
        throw new Transaction.TransactionNotFoundException("Transaction with ID " + id + " does not exist");
    }
    
    @Override
    public Transaction[] transformTransactionList() {
        Transaction []transactions =  new Transaction[this.listSize];
        Transaction current = head;
        int i = 0;
        while (current != null) {
            transactions[i++] = Transaction.copyTransaction(current);
            current = current.getNextTransaction();
        }
        return transactions;
    }

    public Transaction getHead() {
        return this.head;
    }
}