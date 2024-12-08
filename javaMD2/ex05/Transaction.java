package ex05;

import java.util.UUID;

public class Transaction {
    private String identifier;
    private User recipient;
    private User sender;
    private TransactionCategory category;
    private double amount;
    private Transaction nextTransaction;

    public enum TransactionCategory {
        DEBIT,
        CREDIT
    }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String message) {
            super(message);
        }
    }

    public static class IllegalTransactionException extends RuntimeException {
        public IllegalTransactionException (String message) {
            super(message);
        }
    }

    public static Transaction[] createTransaction(User sender, User recipient, double amount) {
        if (sender == null || recipient == null) {
            throw new IllegalArgumentException("Sender and recipient cannot be null");
        }
        if (sender == recipient) {
            throw new IllegalArgumentException("Sender and recipient cannot be the same");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Transaction amount cannot be zero");
        }
        double absoluteAmount = Math.abs(amount);
        try {
            String identifier = UUID.randomUUID().toString();
            Transaction creditTransaction = new Transaction(sender, recipient, amount, identifier, false);
            Transaction debitTransaction = new Transaction(sender, recipient, -amount, identifier, false);
            sender.setBalance(sender.getBalance() - absoluteAmount);
            recipient.setBalance(recipient.getBalance() + absoluteAmount);
            System.out.println(ConsoleColor.GREEN.colorize("Transaction succeeded"));
            return new Transaction[] { creditTransaction, debitTransaction };
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Transaction copyTransaction(Transaction transaction) {
        if (transaction == null)
            throw new IllegalArgumentException("Transaction cannot be zero null");
        return new Transaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount(),
                transaction.getIdentifier(), true);
    }

    // Constructor with improved validation
    private Transaction(User sender, User recipient, double amount, String identifier, boolean isCopyConstructor) {
        this.identifier = identifier;
        this.sender = sender;
        this.recipient = recipient;
        this.nextTransaction = null;
        // Validate transaction amount and set category
        validateAndSetTransaction(amount);

        // Process the transaction
        process(isCopyConstructor);
    }

    // Improved validation method
    private void validateAndSetTransaction(double amount) {
        if (amount > 0) {
            this.category = TransactionCategory.CREDIT;
        } else if (amount < 0) {
            this.category = TransactionCategory.DEBIT;
        } else {
            throw new IllegalArgumentException("Transaction amount cannot be zero");
        }

        // Validate amount based on transaction type
        if (category == TransactionCategory.DEBIT && amount >= 0) {
            throw new IllegalArgumentException("Debit transactions must have negative amounts");
        }
        if (category == TransactionCategory.CREDIT && amount <= 0) {
            throw new IllegalArgumentException("Credit transactions must have positive amounts");
        }

        this.amount = amount;
    }

    // Process transaction with improved error checking
    private void process(boolean isCopyConstructor) {
        double absoluteAmount = Math.abs(amount);
        if (!isCopyConstructor && sender.getBalance() < absoluteAmount) {
            throw new IllegalTransactionException("Insufficient balance for transaction");
        }
    }

    // Existing getter methods remain the same
    public String getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction getNextTransaction() {
        return this.nextTransaction;
    }

    public void setNextTransaction(Transaction transaction) {
        this.nextTransaction = transaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + identifier + '\'' +
                ", sender=" + sender.getName() +
                ", recipient=" + recipient.getName() +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }
}