
public class RecursionVsIteration {

    public static long binomialCoeffRecursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return binomialCoeffRecursive(n - 1, k - 1) + binomialCoeffRecursive(n - 1, k);
    }

    public static long binomialCoeffIterative(int n, int k) {
        long[] C = new long[k + 1];
        C[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                C[j] = C[j] + C[j - 1];
            }
        }
        return C[k];
    }

    public static long productRecursive(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        return arr[index] * productRecursive(arr, index + 1);
    }

    public static long productIterative(int[] arr) {
        long product = 1;
        for (int num : arr) {
            product *= num;
        }
        return product;
    }

    public static int countVowelsRecursive(String str, int index) {
        if (index == str.length()) {
            return 0;
        }
        char c = Character.toLowerCase(str.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRecursive(str, index + 1);
    }

    public static int countVowelsIterative(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean isBalancedRecursive(String str) {
        return checkBalance(str, 0, 0);
    }

    private static boolean checkBalance(String str, int index, int count) {
        if (count < 0) {
            return false;
        }
        if (index == str.length()) {
            return count == 0;
        }
        char c = str.charAt(index);
        if (c == '(') {
            count++; 
        }else if (c == ')') {
            count--;
        }
        return checkBalance(str, index + 1, count);
    }

    public static boolean isBalancedIterative(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                count++; 
            }else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        int n = 20, k = 10;
        int[] arr = {2, 3, 5, 7};
        String testStr = "Recursion vs Iteration";

        System.out.println("=== 二項式係數 ===");
        long start = System.nanoTime();
        System.out.println("遞迴 C(" + n + ", " + k + ") = " + binomialCoeffRecursive(n, k));
        System.out.printf("遞迴時間：%.3f ms\n", (System.nanoTime() - start) / 1e6);

        start = System.nanoTime();
        System.out.println("迭代 C(" + n + ", " + k + ") = " + binomialCoeffIterative(n, k));
        System.out.printf("迭代時間：%.3f ms\n", (System.nanoTime() - start) / 1e6);

        System.out.println("\n=== 陣列元素乘積 ===");
        start = System.nanoTime();
        System.out.println("遞迴乘積 = " + productRecursive(arr, 0));
        System.out.printf("遞迴時間：%.3f ms\n", (System.nanoTime() - start) / 1e6);

        start = System.nanoTime();
        System.out.println("迭代乘積 = " + productIterative(arr));
        System.out.printf("迭代時間：%.3f ms\n", (System.nanoTime() - start) / 1e6);

        System.out.println("\n=== 字串中元音數量 ===");
        start = System.nanoTime();
        System.out.println("遞迴元音數 = " + countVowelsRecursive(testStr, 0));
        System.out.printf("遞迴時間：%.3f ms\n", (System.nanoTime() - start) / 1e6);

        start = System.nanoTime();
        System.out.println("迭代元音數 = " + countVowelsIterative(testStr));
        System.out.printf("迭代時間：%.3f ms\n", (System.nanoTime() - start) / 1e6);

        System.out.println("\n=== 括號配對檢查 ===");
        String balancedStr = "((())())()";
        String unbalancedStr = "(()))(";
        System.out.println("遞迴檢查 (正確): " + isBalancedRecursive(balancedStr));
        System.out.println("遞迴檢查 (錯誤): " + isBalancedRecursive(unbalancedStr));
        System.out.println("迭代檢查 (正確): " + isBalancedIterative(balancedStr));
        System.out.println("迭代檢查 (錯誤): " + isBalancedIterative(unbalancedStr));
    }
}
