import java.util.Scanner;

public class Program {
    
    public static void printArrow (int weekCount, long scoreCount) {
        System.out.printf("Week %d ", weekCount);
        for (int i = 0; i < scoreCount; ++i) {
            System.out.print("=");
        }
        System.out.println(">");
    }
    public static void main(String[] args) {
        int i = 1;
        long result = 0;
        boolean isEven = true;
        int multiplier = 1;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (i == 19) break;
            if (!scanner.hasNextLine()) {
                System.err.println("IllegalArgument ");
                System.exit(-1);
            }

            String line = scanner.nextLine();  // Read the whole line
            Scanner lineScanner = new Scanner(line);  // Use a new scanner to process this line

            if (!lineScanner.hasNext()) {  // If the line is empty, it's invalid
                System.err.println("IllegalArgument 1");
                System.exit(-1);
            }
            String lineStart = "";
            int numberStart = 0;
            if (lineScanner.hasNextInt()) {
                numberStart = lineScanner.nextInt(); // Get the first score
            } else {
                lineStart = lineScanner.next();  // Get the first token
            }

            if (numberStart == 42) {
                break;  // Exit the loop if "42" is encountered
            }

            if (isEven == true && lineStart.equals("Week")) {
                if (lineScanner.hasNextInt()) {  // Check if there's an integer after "Week"
                    int weekNumber = lineScanner.nextInt();  // Get the week number

                    if (weekNumber != i) {
                        System.err.println("IllegalArgument 2");
                        System.out.println(weekNumber + " " + i);
                        System.exit(-1);
                    }

                    // Ensure there are no additional tokens after the week number
                    if (lineScanner.hasNext()) {
                        System.err.println("IllegalArgument 3");
                        System.exit(-1);
                    }
                } else {
                    System.err.println("IllegalArgument 4");
                    System.exit(-1);
                }
                i += 1; //increment i only one a week
            } else if (isEven == false){
                int min = numberStart;
                for (int j = 0; j < 4; j++) {
                    if (lineScanner.hasNextInt()) {
                        int number = lineScanner.nextInt();
                        if (number < min) min = number;
                    } else {
                        System.err.println("IllegalArgument 5");
                        System.exit(-1);    
                    }
                }
                result += multiplier * min;
                multiplier *= 10;
            } else {
                System.err.println("IllegalArgument 6");
                System.exit(-1);
            }
            isEven = !isEven;

            lineScanner.close();
        }
        int count = 1;
        while (result != 0) {
            printArrow(count, result % 10);
            result /= 10;
            count ++;
        }
        scanner.close();
    }
}
