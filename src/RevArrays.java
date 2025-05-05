import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class RevArrays {

    public float findAverage(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return (float) sum / arr.length;
    }

    public int findSmallestElement(int[] arr) {
        int smallest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        return smallest;
    }

    public void evenOddCount(int[] arr) {
        int oddCount = 0;
        int evenCount = 0;
        for (int i : arr) {
            if (i % 2 == 0) {
                evenCount += 1;
            } else {
                oddCount += 1;
            }
        }
        System.out.println("Even Count: " + evenCount);
        System.out.println("Odd Count: " + oddCount);
    }

    public void reverseArray(int[] arr) {
        int[] revarray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            revarray[arr.length - 1 - i] = arr[i];
        }
        System.out.println(Arrays.toString(revarray));
    }

    public void reverseInPlace(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        System.out.println(Arrays.toString(arr));
    }

    public void findSecondLargest(int[] arr) {
        // Arrays.sort(arr);
        // System.out.println(arr[arr.length -2]);

        int largest = arr[0];
        int secondLargest = arr[0];
        // int largest = Integer.MIN_VALUE;
        // int secondLargest = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            }
            if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        System.out.println(secondLargest);
    }

    public void findDuplicateElements(int[] arr) {
        // Sol 1: Legcy way
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    System.out.print(arr[i] + " ");
                }
            }
        }
        System.out.println();
        // Sol 2: HashSet and List
        HashSet<Integer> uniqueElements = new HashSet<>();
        HashSet<Integer> duplicateElements = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (!uniqueElements.add(arr[i])) {
                duplicateElements.add(arr[i]);
            }
        }
        System.out.println(duplicateElements);

    }

    public boolean checkArrayIsSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                System.out.println("Array is not sorted");
                return false;
            }
        }
        System.out.println("Array is sorted");
        return true;
    }

    public void findFrequencyOfElement(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(hashMap);
    }

    public void findHighestFrequencyElement(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], hashMap.getOrDefault(arr[i], 0) + 1);
        }
        int highestFrequencyValue = 0;
        int highestFrequencyElement = 0;
        for (Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > highestFrequencyValue) {
                highestFrequencyElement = entry.getKey();
                highestFrequencyValue = entry.getValue();
            }
        }
        System.out.println(highestFrequencyElement);
    }

    public void mergeTwoSortedArrays(int[] arr1, int[] arr2) {
        int[] mergedarray = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                mergedarray[k++] = arr1[i++];
            } else {
                mergedarray[k++] = arr2[j++];
            }
        }
        while (i < arr1.length)
            mergedarray[k++] = arr1[i++];
        while (j < arr2.length)
            mergedarray[k++] = arr2[j++];
        System.out.println(Arrays.toString(mergedarray));
    }

    public void findCommonElementsInArrays(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (!hashMap.containsKey(arr1[i])) {
                hashMap.put(arr1[i], 1);
            }
        }

        for (int i = 0; i < arr2.length; i++) {
            hashMap.put(arr2[i], hashMap.getOrDefault(arr2[i], 0) + 1);
        }

        HashSet<Integer> set = new HashSet<>();
        for (Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > 1) {
                set.add(entry.getKey());
            }
        }
        System.out.println(set);
    }

    public boolean checkTwoArraysAreEqual(int[] arr1, int[] arr2) {

        if (arr1.length != arr2.length)
            return false;

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                System.out.println("Both are not equal");
                return false;
            }
        }
        System.out.println("Both are equal");
        return true;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 7, 9, 3, 0, -1, 2, 1, 4, 4, 4, 4 };
        RevArrays revarrays = new RevArrays();
        System.out.println(revarrays.findAverage(arr));
        System.out.println(revarrays.findSmallestElement(arr));
        revarrays.evenOddCount(arr);
        revarrays.reverseArray(arr);
        revarrays.reverseInPlace(arr);
        revarrays.findSecondLargest(arr);
        revarrays.findDuplicateElements(arr);
        revarrays.checkArrayIsSorted(arr);
        revarrays.checkArrayIsSorted(new int[] { 1, 2, 3, 4, 5, 6, 7 });
        revarrays.findFrequencyOfElement(arr);
        revarrays.findHighestFrequencyElement(arr);
        revarrays.checkTwoArraysAreEqual(new int[] { 1, 2, 3, 4, 5, 6, 7 }, new int[] { 1, 2, 3, 4, 5, 6, 7 });
        revarrays.checkTwoArraysAreEqual(new int[] { 1, 2, 3, 4, 5, 6, 7 }, arr);
        revarrays.findCommonElementsInArrays(arr, new int[] { 1, 55, 9, 77 });
        revarrays.mergeTwoSortedArrays(new int[] { 1, 2, 3, 4, 15, 16, 17 }, new int[] { 1, 2, 13, 14, 55, 66, 67 });
    }
}
