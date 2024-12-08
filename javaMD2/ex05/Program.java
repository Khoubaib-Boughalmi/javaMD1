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
                throw new IllegalArgumentException("Wrong program flag");
            }
            Mode mode = args[0].equals("dev") ? Mode.DEV : Mode.PRODUCTION;

            Menu menu = new Menu();

            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    try {
                        menu.displayMenu(mode);
                        String input = scanner.nextLine().trim();
                        int option;

                        try {
                            option = Integer.parseInt(input);
                        } catch (NumberFormatException e) {
                            System.out.println(ConsoleColor.RED.colorize("Please provide a valid option"));
                            System.out.println("---------------------------------------------------------");
                            continue;
                        }
                        if (mode == Mode.DEV && option == 7 || mode == Mode.PRODUCTION && option == 5) break;
                        menu.processOption(mode, option, scanner);

                    } catch (Exception e) {
                        System.out.println(ConsoleColor.RED.colorize(e.getMessage()));
                    }
                    System.out.println("---------------------------------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColor.RED.colorize(e.getMessage()));
        }
    }
}