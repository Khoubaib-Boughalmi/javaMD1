package ex00;
import java.util.UUID;

public class Transaction {
    private String identifier;
    private User recipient;
    private User sender;
    private TransactionCategory category;
    private double amount;

    public enum TransactionCategory {
        DEBIT,
        CREDIT
    }

    public static Transaction createTransaction(User sender, User recipient, double amount) {
        try {
            Transaction transaction = new Transaction(sender, recipient, amount);
            System.out.println("Transaction succeeded");
            return transaction;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Constructor with improved validation
    private Transaction(User sender, User recipient, double amount) {
        this.identifier = UUID.randomUUID().toString();
        this.sender = sender;
        this.recipient = recipient;
        
        // Validate transaction amount and set category
        validateAndSetTransaction(amount);
        
        // Process the transaction
        process();
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
    private void process() {
        double absoluteAmount = Math.abs(amount);
        
        if (sender.getBalance() < absoluteAmount) {
            throw new IllegalArgumentException("Insufficient balance for transaction");
        }

        sender.setBalance(sender.getBalance() - absoluteAmount);
        recipient.setBalance(recipient.getBalance() + absoluteAmount);
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