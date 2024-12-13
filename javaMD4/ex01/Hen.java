package ex01;

public class Hen implements Runnable {

    private final int count;
    private final Helper helper;

    public Hen (int count, Helper helper) {
        this.count = count;
        this.helper = helper;

    }
    
    @Override
    public void run() {
        for (int i=0; i < count; ++i) {
            try {
                helper.printHen();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}