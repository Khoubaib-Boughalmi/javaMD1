package ex00;

public class Helper {
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
}