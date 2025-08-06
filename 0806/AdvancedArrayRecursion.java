
import java.util.Arrays;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(arr, left, right);
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }

    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        return merge(arr1, 0, arr2, 0);
    }

    private static int[] merge(int[] arr1, int i, int[] arr2, int j) {
        if (i == arr1.length) {
            return Arrays.copyOfRange(arr2, j, arr2.length);
        }
        if (j == arr2.length) {
            return Arrays.copyOfRange(arr1, i, arr1.length);
        }
        if (arr1[i] < arr2[j]) {
            int[] rest = merge(arr1, i + 1, arr2, j);
            int[] result = new int[rest.length + 1];
            result[0] = arr1[i];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        } else {
            int[] rest = merge(arr1, i, arr2, j + 1);
            int[] result = new int[rest.length + 1];
            result[0] = arr2[j];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        }
    }

    public static int findKthSmallest(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Invalid k");
        }
        int[] copy = Arrays.copyOf(arr, arr.length);
        quickSort(copy, 0, copy.length - 1);
        return findKthRecursive(copy, k, 0);
    }

    private static int findKthRecursive(int[] arr, int k, int index) {
        if (index == k - 1) {
            return arr[index];
        }
        return findKthRecursive(arr, k, index + 1);
    }

    public static boolean hasSubsetSum(int[] arr, int target, int index) {
        if (target == 0) {
            return true;
        }
        if (index == arr.length) {
            return false;
        }
        if (arr[index] > target) {
            return hasSubsetSum(arr, target, index + 1);
        }
        return hasSubsetSum(arr, target - arr[index], index + 1) || hasSubsetSum(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] arrUnsorted = {9, 4, 7, 1, 6, 3};

        System.out.println("=== 快速排序 ===");
        quickSort(arrUnsorted, 0, arrUnsorted.length - 1);
        System.out.println(Arrays.toString(arrUnsorted));

        System.out.println("\n=== 合併兩個已排序陣列 ===");
        int[] merged = mergeSortedArrays(arr1, arr2);
        System.out.println(Arrays.toString(merged));

        System.out.println("\n=== 尋找第 k 小元素 ===");
        System.out.println("第 3 小元素: " + findKthSmallest(arrUnsorted, 3));

        System.out.println("\n=== 子序列總和判斷 ===");
        int[] arr3 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.printf("陣列 %s 是否存在子序列總和等於 %d：%s\n",
                Arrays.toString(arr3), target, hasSubsetSum(arr3, target, 0));
    }
}
