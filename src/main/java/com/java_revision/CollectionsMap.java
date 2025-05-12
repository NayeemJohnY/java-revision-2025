package com.java_revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsMap {

    public static void main(String[] args) {
        // Problem 1: Find the first non-repeating character in a string.
        LinkedHashMap<Character, Integer> charSeq = new LinkedHashMap<>();
        String str = "Learn Java Live with World in VS Code";
        for (char ch : str.toCharArray()) {
            charSeq.put(ch, charSeq.getOrDefault(ch, 0)+ 1);
        }
        for (Map.Entry<Character, Integer> entry : charSeq.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("First non-repeating character: " + entry.getKey());
                break;
            }
        }


        // Problem 2: Group words that are anagrams using a Map<String, List<String>>.
        String[] words =  {"bat", "tab", "tap", "pat", "rat", "tar", "art"};
        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (String word : words) {
            char [] chars = word.toCharArray();
            Arrays.sort(chars);
            String string = String.copyValueOf(chars);
            List<String> list = anagramsMap.getOrDefault(string, new ArrayList<>());
            list.add(word);
            anagramsMap.put(string, list);
        }
        System.out.println(anagramsMap);

        // Problem 3: Sort a HashMap by its values.
        Map<String, Integer> sortableMap = new HashMap<>();
        String[] stringWords = {"Testing.com", "SDET", "Java", "Groups", "Source", "Destination"};
        for (String string : stringWords) {
            sortableMap.put(string, string.length());
        }

        System.out.println(sortableMap);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(sortableMap.entrySet());
        Collections.sort(list, (e1, e2) -> e1.getValue() - e2.getValue());
        // Collections.sort(list, new Comparator<Map.Entry<String , Integer>>() {

        //     @Override
        //     public int compare(Map.Entry<String , Integer> o1, Map.Entry<String, Integer> o2) {
        //             return o1.getValue() - o2.getValue();
        //     }
            
        // });
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<String,Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        System.out.println(sortedMap);

        // Problem 4: Count character frequency using HashMap and print in insertion order.
        LinkedHashMap<Character, Integer> sortedFreqMap = new LinkedHashMap<>();
        str = "It is Collection Map Interface Problem Solving";
        for (char ch : str.toCharArray()) {
            sortedFreqMap.put(ch, sortedFreqMap.getOrDefault(ch, 0)+ 1);
        }
        System.out.println(sortedFreqMap);
        System.out.println("=======================================================");

        // Problem 5: Implement a simple cache using LinkedHashMap with access order.
        LinkedHashMap<String, Integer>  accessOrderMap = new LinkedHashMap<>(10, 0.8f, true );
        for (String string : stringWords) {
            accessOrderMap.put(string, string.length());
        }
        System.out.println(accessOrderMap);
        accessOrderMap.put("Hello Word", 6);
        System.out.println(accessOrderMap);
        accessOrderMap.get("SDET");
        System.out.println(accessOrderMap);
        accessOrderMap.get("Java");
        System.out.println(accessOrderMap);
        accessOrderMap.put("Welcome Word", 6);
        System.out.println(accessOrderMap);
        accessOrderMap.get("Java");
        accessOrderMap.put("Welcome Java", 6);
        System.out.println(accessOrderMap);
        accessOrderMap.put("Great Java", 6);
        accessOrderMap.get("Java");
        System.out.println(accessOrderMap);
        accessOrderMap.put("Great Programing", 6);
        System.out.println(accessOrderMap);
        accessOrderMap.put("Great Tool", 6);
        System.out.println(accessOrderMap);
    }

}
