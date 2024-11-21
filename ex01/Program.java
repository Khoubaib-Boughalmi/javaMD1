public class Program {

    public static void primeCheck(int nb) {
        int i = 2;
        while (i <= nb / 2) {
            if (nb % i == 0) {
                System.out.println("false", i - 1);
                break;
            }
            i += 1;
        }
        System.out.println("true", i - 1);    
    }

    public static void main(String[] args) {
        int nb = Scanner().Interger.parseInt();
        if (nb < 2) {
            System.out.println("IllegalArgument");
            exit(-1);
        }

        primeCheck(nb);
    }
}