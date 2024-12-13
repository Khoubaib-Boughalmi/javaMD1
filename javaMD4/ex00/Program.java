package ex00;
public class Program {
    public static void main(String []args) {
        int count = -1;

        try {
            count = Helper.validateAndParseInput(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        Egg eggThread = new Egg(count);
        Thread henThread = new Thread(new Hen(count));

        eggThread.start();
        henThread.start();

        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        }

        for(int i=0; i < count; ++i) {
            System.out.println("Human");
        }
    }
}