package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Helper {

    public static int validateAndParseInput(String[] args) throws IllegalArgumentException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }

        String[] keyValuePair = args[0].split("=");
        if (keyValuePair.length != 2 || !keyValuePair[0].equals("-threadsCount")) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }

        try {
            int count = Integer.parseInt(keyValuePair[1]);
            if (count < 0) {
                throw new IllegalArgumentException("Number of threads cannot be negative");
            }
            return count;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Count shoud be a valid number");
        }
    }

    public static ArrayList<String> parseURLs(String path) throws Exception {
        ArrayList<String> urls = new ArrayList<>();
        try (FileReader file = new FileReader(path); BufferedReader reader = new BufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    urls.add(line);
                }
            }
            return urls;
        } catch (Exception e) {
            throw new Exception("Failed to parse URLs file");
        }
    }

    public static void downloadFile(String fileUrl, String outputPath) {
        try {
            URL url = new URI(fileUrl).toURL();
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);
            Files.copy(url.openStream(), Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException | URISyntaxException e) {
            System.err.println("Error downloading file " + fileUrl + ": " + e.getMessage());
        }
    }

    public static void multithreadingDownload(int threadCount, ArrayList<String> urls) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
            for (int i = 1; i <= urls.size(); ++i) {
                final int index = i - 1;
                executor.submit(() -> {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.printf("Thread-%s start download file number %d%n", name.charAt(name.length() - 1), index);
                        downloadFile((String) urls.get(index), "test" + index);
                        System.out.printf("Thread-%s finished download file number %d%n", name.charAt(name.length() - 1), index);
                    } catch (Exception e) {
                        System.err.println("Error downloading file: " + e.getMessage());
                    }

                });
            }
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }
}
