package com.java_revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsSet {

    public void findDuplicateElements(){
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 2, 3, 4));
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> duplicateList = new ArrayList<>();
        for (Integer integer : list) {
            if (!set.add(integer))
                    duplicateList.add(integer);
        }
        System.out.println("Duplicate Elements "+ duplicateList);
    }

    public boolean checkAnagram(String s1, String s2){
        if (s1.length() != s2.length())
            return false;
        
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            set.add(s1.charAt(i));
        }
        for (Character character : s2.toCharArray()) {
            set.remove(character);
        }
        return set.isEmpty();
    }

    public void findCommonElements(){
        Set<String> set1 = new HashSet<>(Arrays.asList("String", "duplicate", "common", "eleements"));
        Set<String> set2 = new HashSet<>(Arrays.asList("String", "duplicate", "uncommon", "eleements"));
        set1.retainAll(set2);
        System.out.println(set1);
    }

    public static void main(String[] args) {
        CollectionsSet learnCollectionsSet = new CollectionsSet();
        // Problem 1
        learnCollectionsSet.findDuplicateElements();

        // Problem 2
        System.out.println(learnCollectionsSet.checkAnagram("silent", "listen"));
        System.out.println(learnCollectionsSet.checkAnagram("silent", "pisten"));

        // Problem 3
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 1,2, 2,4, 4, 1, 3, 3, 1, 6, 8, 5, 5, 7, 5));
        System.out.println(list);
        LinkedHashSet linkedHashSet = new LinkedHashSet<>(list);
        System.out.println(linkedHashSet);

        // Problem 4
        learnCollectionsSet.findCommonElements();

        // Problem 5
        TreeSet<String> treeset = new TreeSet<>();
        treeset.add("Alphabet");
        treeset.add("Alpha");
        treeset.add("Ground");
        treeset.add("Grow");
        treeset.add("Group");
        treeset.add("Group");
        treeset.add("Alphabet");
        System.out.println(treeset);
    }
}
