package ex05;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    void removeTransactionById(String id);

    Transaction[] transformTransactionList();
}