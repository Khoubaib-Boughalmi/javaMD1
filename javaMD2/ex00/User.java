package  ex00;
public class User {
  private int identifier;
  private String name;
  private double balance;


  public User(int identifier, String name, double initialBalance) {
      this.identifier = identifier;
      setName(name);
      setBalance(initialBalance);
  }

    public int getIdentifier() {
      return identifier;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public double getBalance() {
      return balance;
    }

    public void setBalance(double balance) {
      if (balance < 0) {
          System.err.println("Initial balance cannot be negative");
      } else {
        this.balance = balance;
      }
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