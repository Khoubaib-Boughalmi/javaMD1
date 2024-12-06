package  ex00;

public class Program {
    public static void main(String[] args) {
        User alice = new User(1, "Alice", 222.0);
        User bob = new User(2, "Bob", 666.0);

        System.out.println("Initial User States:");
        System.out.println(alice);
        System.out.println(bob);

        Transaction debitTransaction = Transaction.createTransaction(alice,bob, -200.0);
        
        Transaction creditTransaction = Transaction.createTransaction(bob, alice, -30.0);

        System.out.println("\nTransaction Details:");
        System.out.println(debitTransaction);
        System.out.println(creditTransaction);

        // System.out.println("\nUpdated User States:");
        System.out.println(alice);
        System.out.println(bob);
    }
}