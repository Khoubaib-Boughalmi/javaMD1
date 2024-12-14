package ex02;

import java.util.concurrent.atomic.AtomicLong;

public class ThreadSum implements Runnable {

    private final int start;
    private final int end;
    private final int[] randArray;
    private final AtomicLong totalSum;

    public ThreadSum(int[] randArray, int start, int end, AtomicLong totalSum) {
        this.start = start;
        this.end = end;
        this.randArray = randArray;
        this.totalSum = totalSum;
    }

    @Override
    public void run() {
        long sum = Helper.sumArrayElement(randArray, start, end);
        System.out.printf("Thread %s: from %d to %d sum is %d%n",
                Thread.currentThread().getName(), start, end, sum);
        totalSum.addAndGet(sum);
    }
}
