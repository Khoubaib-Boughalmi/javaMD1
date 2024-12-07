package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User alice = new User("Alice", 300.0);
        User bob = new User("Bob", 200.0);

        Transaction transaction1 = Transaction.createTransaction(alice, bob, -200.0);
        System.out.println(transaction1);
        Transaction transaction2 = Transaction.createTransaction(bob, alice, -100.0);
        System.out.println(transaction2);
        Transaction transaction3 = Transaction.createTransaction(alice, bob, -100.0);
        System.out.println(transaction3);

        TransactionsLinkedList transactions = new TransactionsLinkedList();
        transactions.addTransaction(transaction1);
        transactions.addTransaction(transaction2);
        transactions.addTransaction(transaction3);
            
        Transaction []transactionsArray = transactions.transformTransactionList();

        String []transactionsUUID = new String[transactionsArray.length];

        for (int i = 0; i < transactionsArray.length; ++i) {
            transactionsUUID[i] = transactionsArray[i].getIdentifier();
        }
        
        for (int i = 0; i < transactionsArray.length; ++i) {
            transactions.removeTransactionById(transactionsUUID[i]);
        }
        
        transactionsArray = transactions.transformTransactionList();
        
        System.out.println(transactionsArray.length);
        for (int i = 0; i < transactionsArray.length; ++i) {
            System.out.println(transactionsArray[i].getIdentifier());
        }
        transactions.removeTransactionById(UUID.randomUUID().toString());
    }
}