/**
 *
 * @author kboughal
 */

public class Program {
    public static int sumDigits(int nb) {
        int sum = 0;
        int count = 0;
        while (nb > 0) {
            sum += nb % 10;
            nb /= 10;
            count += 1;
        }
        if (count != 6) return -1;
        return sum;
    }

    public static void main (String[] args) {
        int nb = 123456;
        int sum = sumDigits(nb);
        if (sum == -1) return;
        System.out.println(sum);
    }
}