package ex02;

public class Handler {
    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }
    public static void checkValidProgramArgs(String []args) {
        if (args.length != 1) {
            throw new InvalidInputException("Wrong number of Input: Please provide a directory");
        }
    }
    
    public static String parseInputDirectory (String input) {
        String []splitInput = input.split("=");
        if (splitInput.length != 2 || !splitInput[0].equals("--current-folder") || splitInput[1].charAt(0) != '/') {
            throw new InvalidInputException("Please provide a valid flag");
        }
        return splitInput[1];
    }
}
