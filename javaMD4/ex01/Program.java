package ex01;
public class Program {
    public static void main(String []args) {
        int count = -1;

        try {
            count = Helper.validateAndParseInput(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        
        Helper helper = new Helper();

        Egg eggThread = new Egg(count, helper);
        Thread henThread = new Thread(new Hen(count, helper));

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