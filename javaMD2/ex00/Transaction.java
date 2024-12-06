package  ex00;
import java.util.UUID;

public class Transaction {
    private String identifier;
    private User recipient;
    private User sender;
    private TransactionCategory category;
    private double amount;

    public enum TransactionCategory {
        DEBIT,   // Money leaving an account (negative amount)
        CREDIT   // Money entering an account (positive amount)
    }

    public Transaction(User sender, User recipient, double amount, TransactionCategory category) {
        this.identifier = UUID.randomUUID().toString();
        this.sender = sender;
        this.recipient = recipient;
        setCategory(category);
        setAmount(amount);
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public void setAmount(double amount) {
        if (category == TransactionCategory.DEBIT && amount > 0) {
            throw new IllegalArgumentException("Debit transactions must have negative amounts");
        }
        if (category == TransactionCategory.CREDIT && amount < 0) {
            throw new IllegalArgumentException("Credit transactions must have positive amounts");
        }
        this.amount = amount;
    }

    public void process() {
        if (category == TransactionCategory.DEBIT) {
            if (Math.abs(amount) > sender.getBalance()) {
                throw new IllegalStateException("Insufficient balance for debit transaction");
            }
            
            sender.setBalance(sender.getBalance() + amount);  // amount is negative
            recipient.setBalance(recipient.getBalance() - amount);
        } else if (category == TransactionCategory.CREDIT) {
            sender.setBalance(sender.getBalance() + amount);
            recipient.setBalance(recipient.getBalance() - amount);
        }
    }

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