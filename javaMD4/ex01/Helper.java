package ex01;

public class Helper {
    private boolean isEggTurn = true;

    public static int validateAndParseInput(String []args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        
        String []keyValuePair = args[0].split("=");
        if (keyValuePair.length != 2 || !keyValuePair[0].equals("--count")) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }

        int count = -1;
        try {
            count = Integer.parseInt(keyValuePair[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Count shoud be a valid number");
        }

        return count;
    }

    public synchronized void printEgg() throws InterruptedException{
        while (!isEggTurn) {
            wait();
        }

        System.out.println("Egg");
        isEggTurn = !isEggTurn;
        notifyAll();
    }

    public synchronized void printHen() throws InterruptedException{
        while (isEggTurn) {
            wait();
        }

        System.out.println("Hen");
        isEggTurn = !isEggTurn;
        notifyAll();
    }
}