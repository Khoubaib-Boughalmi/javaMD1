import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread running " + Thread.currentThread().getName());
    }
}

public class test {

    public static void main(String[] args) {
        Thread firstThread = new Thread(new MyRunnable());
        Thread secondThread = new Thread(new MyRunnable());
        firstThread.start();
        secondThread.start();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; ++i) {
            try {
                executor.execute(() -> {
                    System.out.println("Running task: " + Thread.currentThread().getName());
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            if (!executor.awaitTermination(0, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("Hello from main");
    }
}