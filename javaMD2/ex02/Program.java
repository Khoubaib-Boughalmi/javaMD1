

public class Program {

  public static void main(String []args) {
    User alice = new User("Alice", 1000);
    User bob = new User("Bob", 500);
    
    System.out.println("Alice's ID: " + alice.getId());
    System.out.println("Bob's ID: " + bob.getId());

    User sed = new User("Sed", 500);
    System.out.println("Sed's ID: " + sed.getId());
  }
}