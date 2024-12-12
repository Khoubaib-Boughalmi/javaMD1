package ex01;

import java.util.ArrayList;
import java.util.TreeSet;

public class Program {
    public static void main(String[] args) {
        ArrayList<String> documentAWords = Handler.parseFile("ex01/inputA.txt");
        ArrayList<String> documentBWords = Handler.parseFile("ex01/inputB.txt");
        TreeSet<String> mergedUniqueWords = Handler.sortAndMergeList(documentAWords, documentBWords);
        ArrayList<Integer> documentAWordFrequencies = Handler.getFrequencyOccurrence(documentAWords, mergedUniqueWords);
        System.out.println(documentAWordFrequencies);
        ArrayList<Integer> documentBWordFrequencies = Handler.getFrequencyOccurrence(documentBWords, mergedUniqueWords);
        System.out.println(documentBWordFrequencies);
        double cosineSimilarity = Handler.calculateSimilarity(documentAWordFrequencies, documentBWordFrequencies);
        System.out.printf("Similarity = %.2f%n", ((double) Math.floor(cosineSimilarity * 100)) / 100);
    }
}