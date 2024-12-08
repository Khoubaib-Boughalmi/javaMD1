package ex05;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

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
            throw new IllegalArgumentException("No input available. Please try again.");
        }
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid input. Please provide a non-empty value.");
        }
        String[] inputList = input.split(" ");
        if (inputList == null || inputList.length != 2) {
            throw new IllegalArgumentException("No input available. Please provde a name and a balance.");
        }
        this.transactionsService.addUser(new User(inputList[0], Double.valueOf(inputList[1])));
        UsersArrayList users =  this.transactionsService.geUserList();
        System.out.println(ConsoleColor.GREEN.colorize("User with id = " + users.getUserById(users.getNumberOfUsers()).getIdentifier() + " is added"));
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
        }        User user = this.transactionsService.geUserList().getUserById(userId);
        System.out.println(user.getName() + " - " + user.getBalance());
    }

    private void handleThirdOption(Scanner scanner) {
        System.out.println("Executing Third Option");
    }

    private void handleFourthOption(Scanner scanner) {
        System.out.println("Executing Fourth Option");
    }

    private void handleFithOption(Scanner scanner) {
        System.out.println("Executing Fith Option");
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