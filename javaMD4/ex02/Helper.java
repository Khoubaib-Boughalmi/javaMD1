package ex02;

public class Helper {

    public static int[] validateAndParseInput(String[] args) throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly two arguments required: --arraySize=X and --threadsCount=Y");
        }

        String[] arraySizeList = args[0].split("=");
        if (arraySizeList.length != 2 || !arraySizeList[0].equals("--arraySize")) {
            throw new IllegalArgumentException("First argument must be in format: --arraySize=X");
        }

        String[] threadCountList = args[1].split("=");
        if (threadCountList.length != 2 || !threadCountList[0].equals("--threadsCount")) {
            throw new IllegalArgumentException("Second argument must be in format: --threadsCount=Y");
        }

        try {
            int arraySize = Integer.parseInt(arraySizeList[1]);
            int threadCount = Integer.parseInt(threadCountList[1]);
            if (arraySize < 1 || threadCount < 1) {
                throw new IllegalArgumentException("Array size && Thread count should be greater than 0");
            }

            if (arraySize > 2000000) {
                throw new IllegalArgumentException("Array size cannot exceed 2,000,000");
            }

            if (threadCount > arraySize) {
                throw new IllegalArgumentException("Thread count cannot exceed array size");
            }
            return new int[] { arraySize, threadCount };
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Array size and thread count must be valid integers");
        }
    }

    public static int[] generateIntArray(int arraySize) {
        int[] randArray = new int[arraySize];

        for (int i = 0; i < arraySize; ++i) {
            randArray[i] = (int) (Math.random() * 1001);
        }
        return randArray;
    }

    public static long sumArrayElement(int[] randArray, int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += randArray[i];
        }
        return sum;
    }

    public static int calculateSectionSize(int arraySize, int threadCount) {
        return (int) Math.floor(arraySize / threadCount) + 1;
    }
}