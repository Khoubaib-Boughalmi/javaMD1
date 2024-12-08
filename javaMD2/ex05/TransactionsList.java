package ex05;

public interface TransactionsList {
    void addTransaction(Transaction transaction);

    Transaction removeTransactionById(String id);

    Transaction[] transformTransactionList();
}