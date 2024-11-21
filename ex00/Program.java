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

    public static void main (String[] args) {
        int nb = 123;
        sum = sumDigits(nb);
        if (sum == -1) return 0;
        system.out.println(sum);
    }
}