package ex05;

import java.util.Scanner;

enum Mode {
    DEV,
    PRODUCTION
}

public class Program {
    public void initProgram(String[] args) {
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                // TODO: flag parsing
                throw new IllegalArgumentException("Wrong program flag");
            }
            Mode mode = args != null && args.length > 0 && args[0].equals("dev")
                    ? Mode.DEV
                    : Mode.PRODUCTION;

            Menu menu = new Menu();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    menu.displayMenu(mode);
                    String input = scanner.nextLine().trim();
                    int option;
                    try {
                        option = Integer.parseInt(input);
                    } catch(Exception e) {
                        System.out.println("Please provide a valid option");
                        continue;
                    }
                    if (mode == Mode.DEV && option == 7 || mode == Mode.PRODUCTION && option == 5) break;
                    menu.processOption(mode, option);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}