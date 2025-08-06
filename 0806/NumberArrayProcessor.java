
import java.util.*;

public class NumberArrayProcessor {

    public static int[] removeDuplicates(int[] array) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        return result;
    }

    public static int findMostFrequent(int[] array) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        int mostFreqNum = array[0];
        for (int num : array) {
            int freq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, freq);
            if (freq > maxFreq) {
                maxFreq = freq;
                mostFreqNum = num;
            }
        }
        return mostFreqNum;
    }

    public static int[][] splitArray(int[] array) {
        int mid = (array.length + 1) / 2;
        int[] part1 = Arrays.copyOfRange(array, 0, mid);
        int[] part2 = Arrays.copyOfRange(array, mid, array.length);
        return new int[][]{part1, part2};
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {2, 4, 6, 8};

        printArray(arr1);
        printArray(removeDuplicates(arr1));
        printArray(mergeSortedArrays(new int[]{1, 2, 4}, new int[]{2, 3, 5}));
        System.out.println(findMostFrequent(arr1));
        int[][] split = splitArray(arr1);
        printArray(split[0]);
        printArray(split[1]);
    }
}
