package ex02;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class Program {
    public static void main(String[] args) {
        try {
            int[] input = Helper.validateAndParseInput(args);
            int arraySize = input[0];
            int threadCount = input[1];
            int[] randArray = Helper.generateIntArray(input[0]);
            System.out.println(Arrays.toString(randArray));
            long sum1 = Helper.sumArrayElement(randArray, 0, randArray.length - 1);
            System.out.println("Sum: " + sum1);
            int sectionSize = Helper.calculateSectionSize(arraySize, threadCount);
            AtomicLong totalSum = new AtomicLong(0);
            Thread[] threadList = new Thread[threadCount];
            for (int i = 0; i < threadCount; ++i) {

                int start = i * sectionSize;
                int end = Math.min((i + 1) * sectionSize, arraySize);

                if (start < end) {
                    threadList[i] = new Thread(new ThreadSum(randArray, start, end - 1, totalSum));
                }
            }

            for (Thread th : threadList) {
                if (th != null) {
                    th.start();
                }
            }

            for (Thread th : threadList) {
                if (th != null) {
                    try {
                        th.join();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            System.out.println("Sum by threads: " + totalSum);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}