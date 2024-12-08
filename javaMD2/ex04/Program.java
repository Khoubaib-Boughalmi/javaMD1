package ex04;

public class Program {

    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService(new UsersArrayList());

        User alice = new User("Alice", 300.0);
        User bob = new User("Bob", 200.0);
        User sed = new User("Sed", 150.0);

        transactionsService.addUser(alice);
        transactionsService.addUser(bob);
        transactionsService.addUser(sed);

        User[] users = transactionsService.geUserList().getUsersList();
        for (int i = 0; i < users.length && users[i] != null; i++) {
            System.out.println(users[i].getName() + " " + users[i].getIdentifier() + " has a balance of " + transactionsService.getUserBalance(users[i]));
        }
        try {
          transactionsService.createTransferTransaction(1, 2, 100.0);
          transactionsService.createTransferTransaction(2, 1, 50.0);
          transactionsService.createTransferTransaction(3, 1, 150.0);
          transactionsService.createTransferTransaction(3, 1, 150.0);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

        System.out.println("--------------------------------");

        Transaction[] aliceTransactions = transactionsService.getUserTransferTransactions(alice);
        for (int i = 0; i < aliceTransactions.length && aliceTransactions[i] != null; i++) {
          System.out.println("Alice Transaction's Details:");
          System.out.println("  ID: " + aliceTransactions[i].getIdentifier());
          System.out.println("  Amount: " + aliceTransactions[i].getAmount());
          System.out.println("  Sender: " + aliceTransactions[i].getSender().getName());
          System.out.println("  Recipient: " + aliceTransactions[i].getRecipient().getName());
          System.out.println("--------------------------------");
        }
        
        Transaction[] bobTransactions = transactionsService.getUserTransferTransactions(bob);
        for (int i = 0; i < bobTransactions.length && bobTransactions[i] != null; i++) {
            System.out.println("Bob Transaction's Details:");
            System.out.println("  ID: " + bobTransactions[i].getIdentifier());
            System.out.println("  Amount: " + bobTransactions[i].getAmount());
            System.out.println("  Sender: " + bobTransactions[i].getSender().getName());
            System.out.println("  Recipient: " + bobTransactions[i].getRecipient().getName());
            System.out.println("--------------------------------");
        }
        Transaction[] sedTransactions = transactionsService.getUserTransferTransactions(sed);

        for (int i = 0; i < sedTransactions.length && sedTransactions[i] != null; i++) {
            System.out.println("Sed Transaction's Details:");
            System.out.println("  ID: " + sedTransactions[i].getIdentifier());
            System.out.println("  Amount: " + sedTransactions[i].getAmount());
            System.out.println("  Sender: " + sedTransactions[i].getSender().getName());
            System.out.println("  Recipient: " + sedTransactions[i].getRecipient().getName());
            System.out.println("--------------------------------");
        }

        try {
          transactionsService.removeTransferTransaction(alice, aliceTransactions[0].getIdentifier());
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

        aliceTransactions = transactionsService.getUserTransferTransactions(alice);
        System.out.println("--------------------------------");
        for (int i = 0; i < aliceTransactions.length && aliceTransactions[i] != null; i++) {
          System.out.println("Alice Transaction's Details:");
          System.out.println("  ID: " + aliceTransactions[i].getIdentifier());
          System.out.println("  Amount: " + aliceTransactions[i].getAmount());
          System.out.println("  Sender: " + aliceTransactions[i].getSender().getName());
          System.out.println("  Recipient: " + aliceTransactions[i].getRecipient().getName());
          System.out.println("--------------------------------");
        }
    }

}
