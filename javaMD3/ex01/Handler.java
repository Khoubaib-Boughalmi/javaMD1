package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Handler {

    public static ArrayList<String> parseFile(String path) {
        ArrayList<String> arrayListWords = new ArrayList<>();
        try (
                FileReader reader = new FileReader(path); BufferedReader buffer = new BufferedReader(reader)) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = buffer.readLine()) != null) {
                content.append(line.trim()).append('\n');
            }

            String[] words = content.toString().split("\\s+");
            if (words.length > 0) {
                arrayListWords = new ArrayList<>(words.length);
                arrayListWords.addAll(Arrays.asList(words));
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return arrayListWords;
    }

    public static ArrayList<Integer> getFrequencyOccurrence(ArrayList<String> words, TreeSet<String> uniqueWords) {
        ArrayList<Integer> frequency = new ArrayList<>();
        for (String word : uniqueWords) {
            if (!word.trim().isEmpty()) {
                frequency.add(Collections.frequency(words, word));
            }
        }

        return frequency;
    }

    public static TreeSet<String> sortAndMergeList(ArrayList<String> lst1, ArrayList<String> lst2) {
        TreeSet<String> sortedUniqueSet = new TreeSet<>(lst1);
        sortedUniqueSet.addAll(lst2);
        return sortedUniqueSet;
    }

    private static int calculateDotProduct(ArrayList<Integer> vectorA, ArrayList<Integer> vectorB) {
        int product = 0;
        for (int i = 0; i < vectorA.size(); i++) {
            product += vectorA.get(i) * vectorB.get(i);
        }
        return  product;
    }

    private static double calculateVectorNorm(ArrayList<Integer> vectorA) {
       double norm = calculateDotProduct(vectorA, vectorA);
       return Math.sqrt(norm);
    }
    

    public static double calculateSimilarity(ArrayList<Integer> vectorA, ArrayList<Integer> vectorB) {
        if (vectorA.size() != vectorB.size()) {
            throw new IllegalArgumentException("Vectors must be of the same size");
        }
        int numerator = calculateDotProduct(vectorA, vectorB);
        double normA = calculateVectorNorm(vectorA);
        double normB = calculateVectorNorm(vectorB);
        if (normA == 0 || normB == 0) return 0.0;
        return numerator/(normA * normB);
    }
}
