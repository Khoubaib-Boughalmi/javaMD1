package ex02;

import java.io.File;
import java.util.Date;

public class Tools {
    public static class InvalidDirectoryException extends RuntimeException {
        public InvalidDirectoryException(String message) {
            super(message);
        }
    }
    public static void ls(String currentDir) {
        File directory = new File(currentDir);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new InvalidDirectoryException(currentDir + " doesn't exist");
        }

        File[] files = directory.listFiles();
        for(File file: files) {
            String name = file.getName();
            long size = file.length();
            System.out.printf("%s %d KB%n", name, size);
        }
    }
}
