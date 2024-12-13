package ex01;

public class Egg extends Thread {

    private final int count;
    private final Helper helper;

    public Egg (int count, Helper helper) {
        this.count = count;
        this.helper = helper;
    }
    
    @Override
    public void run() {
        for (int i=0; i < count; ++i) {
            try {
                this.helper.printEgg();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}