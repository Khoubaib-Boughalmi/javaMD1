package  ex00;
public class Program {
  public static void main(String[] args) {
      User alice = new User(1, "Alice", 1000.0);
      User bob = new User(2, "Bob", 500.0);

      System.out.println("Initial User States:");
      System.out.println(alice);
      System.out.println(bob);

      try {
          Transaction debitTransaction = new Transaction(
              alice,   
              bob,     
              -200.0, 
              Transaction.TransactionCategory.DEBIT
          );
          
          // Create a credit transaction (money entering Bob's account)
          Transaction creditTransaction = new Transaction(
              bob,   
              alice,   
              200.0,
              Transaction.TransactionCategory.CREDIT
          );

          debitTransaction.process();
          creditTransaction.process();

          System.out.println("\nTransaction Details:");
          System.out.println(debitTransaction);
          System.out.println(creditTransaction);

          System.out.println("\nUpdated User States:");
          System.out.println(alice);
          System.out.println(bob);

      } catch (IllegalArgumentException | IllegalStateException e) {
          System.out.println("Transaction Error: " + e.getMessage());
      }
  }
}