import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Strings {
    public void countVowels(String str) {
        int vowelsCount = 0;
        for (char ch : str.toCharArray()) {
            ch = Character.toLowerCase(ch);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelsCount++;
            }
        }
        System.out.println(vowelsCount);
    }

    public void reverseString(String str) {
        StringBuilder reverse = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        System.out.println(reverse);
    }

    public void checkPalindromeString(String str) {
        StringBuilder reverse = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse.append(str.charAt(i));
        }
        if (str.equalsIgnoreCase(reverse.toString())) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not palindrome");
        }
    }

    public void countWords(String str) {
        String[] words = str.split(" ");
        words = str.trim().split("\\s+");
        System.out.println(words.length);
    }

    public void removeDigits(String str) {
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                result.append(ch);
            }
        }
        System.out.println(result);

    }

    public void frequency(String str) {
        HashMap<Character, Integer> freqencyMap = new HashMap<>();
        for (char ch : str.toCharArray()) {
            freqencyMap.put(ch, freqencyMap.getOrDefault(ch, 0) + 1);
            // if (! freqencyMap.containsKey(ch)){
            // freqencyMap.put(ch, 1);
            // }
            // else {
            // freqencyMap.put(ch, freqencyMap.get(ch)+ 1);
            // }
        }
        System.out.println(freqencyMap);
    }

    public void toogleCaseCharacters(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                stringBuilder.append(Character.toLowerCase(ch));
            } else if (Character.isLowerCase(ch)) {
                stringBuilder.append(Character.toUpperCase(ch));
            } else {
                stringBuilder.append(ch);
            }
        }
        System.out.println(stringBuilder);
    }

    public void removeDuplicateChars(String str) {
        HashSet<Character> hashSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (!hashSet.contains(ch)) {
                hashSet.add(ch);
                stringBuilder.append(ch);
            }
        }


        System.out.println(stringBuilder);
    }

    public void findFirstNonRepeatingCharacter(String str) {
        LinkedHashMap<Character, Integer> freqMap = new LinkedHashMap<>();

        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println("First non-repeating character: " + entry.getKey());
                return;
            }
        }

        System.out.println("No non-repeating character found.");
    }

    public void checkAnagram(String str1, String str2) {
        // Sol 1
        // if (str1.length() != str2.length()){
        // System.out.println("false");
        // return;
        // }

        // for (Character ch : str1.toCharArray()){
        // if (str2.indexOf(ch) == -1) {
        // System.out.println("false");
        // return;
        // }
        // }
        // System.out.println("true");

        // Sol 2
        // if (str1.length() != str2.length()) {
        //     System.out.println("false");
        //     return;
        // }

        // char[] arr1 = str1.toCharArray();
        // char[] arr2 = str2.toCharArray();

        // Arrays.sort(arr1);
        // Arrays.sort(arr2);

        // boolean result = Arrays.equals(arr1, arr2);
        // System.out.println(result);

        // Sol 3
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : str1.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        for (char ch : str2.toCharArray()) {
            if (! freqMap.containsKey(ch)){
                System.out.println("false");
                return;
            }
            freqMap.put(ch, freqMap.get(ch) -1);
            if (freqMap.get(ch) == 0 ){
                freqMap.remove(ch);
            }
        }
        System.out.println(freqMap.isEmpty());

    }

    public static void main(String[] args) throws Exception {
        Strings strings = new Strings();
        strings.countVowels("Hello Java");
        strings.reverseString("Java");
        strings.checkPalindromeString("madam");
        strings.countWords("Java is awesome" );
        strings.removeDigits("abc123xyz");
        strings.frequency("banana");
        strings.toogleCaseCharacters("Jass12Va");
        strings.removeDuplicateChars("Java");
        strings.findFirstNonRepeatingCharacter("Java");
        strings.checkAnagram("listen", "silent");
    }
}
