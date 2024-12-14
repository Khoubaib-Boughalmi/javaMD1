package ex03;

import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        try {
            int threadCount = Helper.validateAndParseInput(args);
            ArrayList<String> urls = Helper.parseURLs("URLs.txt");
            Helper.multithreadingDownload(threadCount, urls);            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
