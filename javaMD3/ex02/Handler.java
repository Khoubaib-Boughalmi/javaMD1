package ex02;

import java.io.File;
import java.io.IOException;

import ex02.Tools.InvalidDirectoryException;

public class Handler {
    private static String currentDir;

    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static void checkValidProgramArgs(String[] args) {
        if (args.length != 1) {
            throw new InvalidInputException("Wrong number of Input: Please provide a directory");
        }
    }

    public static void parseInputDirectory(String input) {
        if (input == null || input.isEmpty()) {
            throw new InvalidInputException("Input cannot be null or empty");
        }
    
        String[] splitInput = input.split("=", 2);
        if (splitInput.length != 2 || !splitInput[0].equals("--current-folder") || splitInput[1].isEmpty() || splitInput[1].charAt(0) != '/') {
            throw new InvalidInputException("Invalid input flag. Expected format: --current-folder=/path/to/dir");
        }
    
        currentDir = splitInput[1];
        File directory = new File(currentDir);
    
        if (!directory.exists()) {
            throw new InvalidDirectoryException("The directory '" + currentDir + "' does not exist");
        }
        if (!directory.isDirectory()) {
            throw new InvalidDirectoryException("The path '" + currentDir + "' is not a directory");
        }
    
        try {
            currentDir = directory.getCanonicalPath();
        } catch (IOException e) {
            throw new InvalidInputException(
                    "Failed to resolve the canonical path for the directory: " + e.getMessage());
        }
    }
    

    public static String getCurrentDir() {
        return currentDir;
    }

    public static void setCurrentDir(String path) {
        currentDir = path;
    }

}
