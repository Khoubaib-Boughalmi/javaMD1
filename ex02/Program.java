public class Program {

    public static int sumDigits(int nb) {
        int sum = 0;
        int count = 0
        while (nb) {
            sum += nb % 10;
            nb /= 10
            count += 1
        }
        if (count != 5) return -1;
        return sum;
    }

    public static void isPrime(int nb) {
        int i = 2;
        while (i <= nb / 2) {
            if (nb % i == 0) return false;
            i += 1;
        }
        return true;
    }

    public static void main(String[] args) {
        count_primes = 0;
        while (1) {
            int nb = Scanner().Interger.parseInt();
            if (nb == 42) {
                System.out.println("Count of coffee-request: ", count_primes)
                break;
            }
            if (isPrime(sumDigits(nb))) count_primes += 1;
        }
    }
}