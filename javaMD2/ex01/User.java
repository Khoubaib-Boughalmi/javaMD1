public class User {
  private int     id          = 0;
  private int     balance     = 0;
  private String  name        = "";

  public User(String name, int balance) {
    this.id = UserIdsGenerator.getInstance().generateId();
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

  public int getId() {
    return this.id;
  }

}