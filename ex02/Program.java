
import java.util.Scanner;

public class Program {

    public static int sumDigits(int nb) {
        int sum = 0;
        while (nb > 0) {
            sum += nb % 10;
            nb /= 10;
        }
        return sum;
    }

    public static boolean isPrime(int nb) {
        int i;
        for (i = 2; i * i <= nb; ++i) {
            if (nb % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int count_primes = 0;
        try(Scanner scanner = new Scanner(System.in)){
            while (true) {
                int nb = scanner.nextInt();
                if (nb == 42) {
                    System.out.printf("Count of coffee-request: %d\n",  count_primes);
                    break;
                }
                if (isPrime(sumDigits(nb))) count_primes += 1;
            }
        }
    }
}