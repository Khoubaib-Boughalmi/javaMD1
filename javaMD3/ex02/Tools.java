package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

    public static void mv(String path1, String path2) throws IOException {
        // check if this is an mv or a rename action
        // mv: if the second param is a directory
        // rename: if the second param is a file

        File source = new File(path1).getAbsoluteFile();
        File destination = new File(path2).getAbsoluteFile();
        
        if (!source.exists()) {
            throw new IOException("Source path does not exist: " + path1);
        }

        Path sourcePath = source.toPath();
        Path destinationPath = destination.toPath();
        if (destination.isDirectory()) {
            // move role
            Path target = destinationPath.resolve(source.getName());
            Files.move(sourcePath, target, StandardCopyOption.REPLACE_EXISTING);
        } else {
            // rename role
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        }

    }

    public static void pwd() {
        System.out.println(Handler.getCurrentDir());
    }
}
