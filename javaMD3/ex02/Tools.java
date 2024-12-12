package ex02;

import java.io.File;

public class Tools {
    public static class InvalidDirectoryException extends RuntimeException {
        public InvalidDirectoryException(String message) {
            super(message);
        }
    }

    public static void ls() {
        File directory = new File(Handler.getCurrentDir()).getAbsoluteFile();

        if (!directory.exists() || !directory.isDirectory()) {
            throw new InvalidDirectoryException(Handler.getCurrentDir() + " doesn't exist");
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            String name = file.getName();
            long size = file.length();
            System.out.printf("%s %d KB%n", name, size);
        }
    }

    public static void cd(String path) {
        File newDir = new File(path).getAbsoluteFile();
        if (newDir.exists() && newDir.isDirectory()) {
            try {
                Handler.setCurrentDir(newDir.getCanonicalPath());
            } catch (Exception e) {
                System.err.println("Cannot change directory: " + e.getMessage());
            }
        } else {
            throw new InvalidDirectoryException("Cannot change directory to file type " + path);
        }
    }

    public static void pwd() {
        System.out.println(Handler.getCurrentDir());
    }
}
