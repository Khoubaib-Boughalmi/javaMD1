package ex05;

import java.util.Arrays;
import java.util.Scanner;
import ex05.Transaction.TransactionCategory;

public class Menu {
    private final TransactionsService transactionsService;

    public Menu() {
        this.transactionsService = new TransactionsService(new UsersArrayList());
    }

    public void displayMenu(Mode mode) {
        int number = 0;
        System.out.println(++number + " Add a user");
        System.out.println(++number + " View user balances");
        System.out.println(++number + " Perform a transfer");
        System.out.println(++number + " View all transactions for a specific user");
        if (mode == Mode.DEV) {
            System.out.println(++number + " DEV - remove a transfer by ID");
            System.out.println(++number + " DEV - check transfer validity");
        }
        System.out.println(++number + " Finish execution");
    }

    public void processOption(Mode mode, int option, Scanner scanner) {
        switch (mode) {
            case DEV -> handleDevelopmentMode(option, scanner);
            case PRODUCTION -> handleProductionMode(option, scanner);
        }
    }

    private void handleProductionMode(int option, Scanner scanner) {
        switch (option) {
            case 1 -> handleFirstOption(scanner);
            case 2 -> handleSecondOption(scanner);
            case 3 -> handleThirdOption(scanner);
            case 4 -> handleFourthOption(scanner);
            default -> throw new IllegalArgumentException("Invalid input: Please select a number between 1 and 5.");
        }
    }

    private void handleDevelopmentMode(int option, Scanner scanner) {
        switch (option) {
            case 1 -> handleFirstOption(scanner);
            case 2 -> handleSecondOption(scanner);
            case 3 -> handleThirdOption(scanner);
            case 4 -> handleFourthOption(scanner);
            case 5 -> handleFithOption(scanner);
            case 6 -> handleSixthOption(scanner);
            default -> throw new IllegalArgumentException("Invalid input: Please select a number between 1 and 7.");
        }
    }

    public void startMenu(Mode mode) {
        displayMenu(mode);
    }

    private void handleFirstOption(Scanner scanner) {
        System.out.println("Enter a user name and a balance:");
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Incorrect Input. Please try again.");
        }
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a non-empty value.");
        }
        String[] inputList = input.split(" ");
        if (inputList == null || inputList.length != 2) {
            throw new IllegalArgumentException("Incorrect Input. Please provde a name and a balance.");
        }
        this.transactionsService.addUser(new User(inputList[0], Double.valueOf(inputList[1])));
        UsersArrayList users = this.transactionsService.geUserList();
        System.out.println(ConsoleColor.GREEN.colorize(
                "User with id = " + users.getUserById(users.getNumberOfUsers()).getIdentifier() + " is added"));
    }

    private void handleSecondOption(Scanner scanner) {
        int userId;
        System.out.println("Enter a user ID");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a non-empty value.");
        }

        try {
            userId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a numeric user ID.");
        }
        User user = this.transactionsService.geUserList().getUserById(userId);

        System.out.println(ConsoleColor.GREEN.colorize(user.getName() + " - " + user.getBalance()));
    }

    private void handleThirdOption(Scanner scanner) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a non-empty value.");
        }

        String[] inputList = input.split(" ");
        if (inputList == null || inputList.length != 3) {
            throw new IllegalArgumentException("Incorrect Input. Please provde a Sender ID Receiver ID and an Amount");
        }
        int senderId;
        int receiverId;
        double amount;
        try {
            senderId = Integer.parseInt(inputList[0]);
            receiverId = Integer.parseInt(inputList[1]);
            amount = Double.parseDouble(inputList[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a numeric user IDs and amount.");
        }
        this.transactionsService.createTransferTransaction(senderId, receiverId, amount);
    }

    private void handleFourthOption(Scanner scanner) {
        System.out.println("Enter a user ID");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a non-empty value.");
        }

        int userId;
        
        try {
            userId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a numeric user ID");
        }

        User user = this.transactionsService.geUserList().getUserById(userId);
        Transaction[] transactions = this.transactionsService.getUserTransferTransactions(user);
        for (Transaction t : transactions) {
            if (t.getCategory() == TransactionCategory.DEBIT) {
                System.out.println(ConsoleColor.GREEN.colorize("To " + t.getRecipient().getName() + " " + t.getAmount() + " (id = "+ t.getRecipient().getIdentifier() +") with id = " + t.getIdentifier()));
            } else if (t.getCategory().equals(TransactionCategory.CREDIT)) {
                System.out.println(ConsoleColor.GREEN.colorize("From " + t.getSender().getName() + " " + t.getAmount() + " (id = "+ t.getRecipient().getIdentifier() +") with id = " + t.getIdentifier()));
            }
        }
    }

    private void handleFithOption(Scanner scanner) {
        System.out.println("Enter a user ID and a transfer ID");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a non-empty value.");
        }

        String[] inputList = input.split(" ");
        if (inputList == null || inputList.length != 2) {
            throw new IllegalArgumentException("Incorrect Input. Please provde a User ID and Transaction ID");
        }
        int userId;
        String transactionId;
        try {
            userId = Integer.parseInt(inputList[0]);
            transactionId = inputList[1].trim();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please provide a numeric user IDs and amount.");
        }

        User user = this.transactionsService.geUserList().getUserById(userId);
        System.out.println("user " + user);
        System.out.println("Trans " + Arrays.toString(this.transactionsService.getUserTransferTransactions(user)));
        try {
            Transaction transaction = this.transactionsService.removeTransferTransaction(user, transactionId);
            // Transfer To Mike(id = 2) 150 removed
            if (transaction.getCategory() == TransactionCategory.DEBIT) {
                System.out.println(ConsoleColor.GREEN.colorize("Transfer To " + transaction.getRecipient().getName() + " (id = "+ transaction.getRecipient().getIdentifier() +") " + transaction.getAmount() +  "removed "));
            } else if (transaction.getCategory().equals(TransactionCategory.CREDIT)) {
                System.out.println(ConsoleColor.GREEN.colorize("Transfer From " + transaction.getSender().getName() + " (id = "+ transaction.getRecipient().getIdentifier() +") " + transaction.getAmount() +  "removed "));
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED.colorize(e.getMessage()));
        }
    }

    private void handleSixthOption(Scanner scanner) {
        System.out.println("Executing Sixth Option");
    }

    private void handleConditionalOption1() {
        System.out.println("Executing Conditional Option 1");
    }

    private void handleConditionalOption2() {
        System.out.println("Executing Conditional Option 2");
    }
}