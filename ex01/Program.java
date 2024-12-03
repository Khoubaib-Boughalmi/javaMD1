import java.util.Scanner;
 
public class Program {

    public static void primeCheck(int nb) {
        int i;
        for (i = 2; i * i <= nb; ++i) {
            if (nb % i == 0) {
                System.out.printf("false %d\n", (i - 1));
                return;
            }
        }
        System.out.printf("true %d\n", (i - 1));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int nb = scanner.nextInt();
            
            if (nb < 2) {
                System.out.println("IllegalArgument");
                System.exit(-1);
            }

            primeCheck(nb);
        }
    }
}