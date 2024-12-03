import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 18; ++i) {
            if (!scanner.hasNextLine()) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            String line = scanner.nextLine();  // Read the whole line
            Scanner lineScanner = new Scanner(line);  // Use a new scanner to process this line

            if (!lineScanner.hasNext()) {  // If the line is empty, it's invalid
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            String input = lineScanner.next();  // Get the first token

            if (input.equals("42")) {
                break;  // Exit the loop if "42" is encountered
            }

            if (input.equals("Week")) {
                if (lineScanner.hasNextInt()) {  // Check if there's an integer after "Week"
                    int weekNumber = lineScanner.nextInt();  // Get the week number

                    if (weekNumber != i + 1) {
                        System.err.println("IllegalArgument");
                        System.exit(-1);
                    }

                    // Ensure there are no additional tokens after the week number
                    if (lineScanner.hasNext()) {
                        System.err.println("IllegalArgument");
                        System.exit(-1);
                    }
                } else {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
            } else {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }

            lineScanner.close();  // Close the line-specific scanner
        }

        scanner.close();
    }
}
