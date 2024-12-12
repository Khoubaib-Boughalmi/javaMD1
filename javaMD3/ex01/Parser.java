package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Parser {
  private Map<String, Integer> wordCount;

  public Parser() {
    this.wordCount = new HashMap<>();
    System.out.println("Parser constructor");
  }
  public static ArrayList<String> parseFile(String path) {
    ArrayList<String> arrayListWords = new ArrayList<>();
    try(
      FileReader reader = new FileReader(path);
      BufferedReader buffer = new BufferedReader(reader)) {
      String line;
      StringBuilder content = new StringBuilder("");  
      while ((line = buffer.readLine()) != null) {
          content.append(line).append('\n');
      }
      String[] words = content.toString().split("\\s+");
      Collections.addAll(arrayListWords, words);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return arrayListWords;
  }

  public static ArrayList<Integer> getFrequencyOccurrence(ArrayList<String> words, Set<String> uniqueWords) {
    ArrayList<Integer> frequency = new ArrayList<>();

    for (String word: uniqueWords) {
      frequency.add(Collections.frequency(words, word));
    }

    return frequency;
  }
}
