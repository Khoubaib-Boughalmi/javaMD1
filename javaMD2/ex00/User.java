public class User {
  private int     balance     = 0;
  private int     identifier  = 0;
  private String  name        = "";

  public User(int identifier, String name, int balance) {
    this.identifier = identifier;
    this.name = name;
    if (balance < 0) return;
    this.balance = balance;
  }

  public int getBalance() {
    return this.balance;
  }

  public void setBalance(int newBalance) {
    if (balance < 0) return;
    this.balance = newBalance;
  }

  public String getName() {
    return this.name;
  }

  public int getIdentifier() {
    return this.identifier;
  }

}