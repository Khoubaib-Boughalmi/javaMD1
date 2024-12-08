package ex05;

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


    public void processOption(Mode mode, int option) {
        switch (mode) {
            case DEV -> handleDevelopmentMode(option);
            case PRODUCTION -> handleProductionMode(option);
        }
    }

    private void handleProductionMode(int option) {
        switch (option) {
            case 1 -> handleFirstOption();
            case 2 -> handleFirstOption();
            case 3 -> handleFirstOption();
            case 4 -> handleFirstOption();
            case 5 -> handleFirstOption();
            default -> throw new IllegalArgumentException("Invalid input: Please select a number between 1 and 5.");
        }
    }

    private void handleDevelopmentMode(int option) {
        switch (option) {
            case 1 -> handleFirstOption();
            case 2 -> handleFirstOption();
            case 3 -> handleFirstOption();
            case 4 -> handleFirstOption();
            case 5 -> handleFirstOption();
            case 6 -> handleFirstOption();
            case 7 -> handleFirstOption();
            default -> throw new IllegalArgumentException("Invalid input: Please select a number between 1 and 7.");
        }
    }

    public void startMenu(Mode mode) {
        displayMenu(mode);
    }

    private void handleFirstOption() {
        System.out.println("Executing First Option");
    }

    private void handleSecondOption() {
        System.out.println("Executing Second Option");
    }

    private void handleThirdOption() {
        System.out.println("Executing Third Option");
    }

    private void handleFourthOption() {
        System.out.println("Executing Fourth Option");
    }

    private void handleConditionalOption1() {
        System.out.println("Executing Conditional Option 1");
    }

    private void handleConditionalOption2() {
        System.out.println("Executing Conditional Option 2");
    }
}