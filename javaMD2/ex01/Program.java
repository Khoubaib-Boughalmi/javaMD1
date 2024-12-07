package ex01;
public class Program {

  public static void main(String []args) {
    User alice = new User("Alice", 1000);
    User bob = new User("Bob", 500);
    User sed = new User("Sed", 500);

    System.out.println(alice);
    System.out.println(bob);
    System.out.println(sed);
  }
}