
import java.util.UUID;

public class Program {

  public static void main(String []args) {
    User alice = new User(0, "Alice", 1000);
    User bob = new User(0, "Bob", 500);

    Transaction transaction = new Transaction(
      UUID.randomUUID(),
      alice,
      bob,
      category.CREDITS,
      800);

    System.out.println("Alice's new balance: " + alice.getBalance());
    System.out.println("Bob's new balance: " + bob.getBalance());
  }
}