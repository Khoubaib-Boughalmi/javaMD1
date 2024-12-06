package ex00;

public class User {
    private int identifier;
    private String name;
    private double balance;

    public User(int identifier, String name, double initialBalance) {
        this.identifier = identifier;
        setName(name);
        
        // Throw an exception for negative balance instead of just printing an error
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Add basic name validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        // Throw an exception for negative balance
        if (balance < 0) {
            throw new IllegalArgumentException("Not enough funds");
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + identifier +
            ", name='" + name + '\'' +
            ", balance=" + balance +
            '}';
    }
}