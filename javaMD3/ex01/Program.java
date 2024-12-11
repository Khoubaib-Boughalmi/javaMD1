package ex01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ArrayList<String> arrayListWords1 = Parser.parseFile("ex01/inputA.txt");
        ArrayList<String> arrayListWords2 = Parser.parseFile("ex01/inputB.txt");
        Set<String> joinedWords  = new HashSet<>(arrayListWords1);
        joinedWords.addAll(arrayListWords2);
        System.out.println(joinedWords);
        // System.out.println(uniqueWords2);
    }
}