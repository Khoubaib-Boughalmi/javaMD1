package ex04;

public class User {
    private int identifier;
    private String name;
    private double balance;
    private TransactionsList transactionsList;


    public User(String name, double initialBalance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        setName(name);
        
        if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative");
            return;
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
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be null or empty");
            return;
        }
        this.name = name.trim();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Not enough funds");
            System.out.println(balance);
            return;
        }
        this.balance = balance;
    }

    public TransactionsList getTransactionsList () {
        return this.transactionsList;
    }

    public void setTransactionsList (Transaction transaction) {
        this.transactionsList.addTransaction(transaction);
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