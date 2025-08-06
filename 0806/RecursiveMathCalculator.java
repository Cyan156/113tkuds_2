
public class RecursiveMathCalculator {

    public static long combination(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static long catalan(int n) {
        if (n == 0) {
            return 1;
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += catalan(i) * catalan(n - 1 - i);
        }
        return sum;
    }

    public static long hanoi(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * hanoi(n - 1) + 1;
    }

    public static boolean isPalindromeNumber(int num) {
        return isPalindromeHelper(num, reverseNumber(num, 0));
    }

    private static int reverseNumber(int num, int rev) {
        if (num == 0) {
            return rev;
        }
        return reverseNumber(num / 10, rev * 10 + num % 10);
    }

    private static boolean isPalindromeHelper(int num, int rev) {
        return num == rev;
    }

    public static void main(String[] args) {
        System.out.println("=== 組合數 C(n, k) ===");
        System.out.println("C(5, 2) = " + combination(5, 2));
        System.out.println("C(6, 3) = " + combination(6, 3));

        System.out.println("\n=== 卡塔蘭數 C(n) ===");
        for (int i = 0; i <= 7; i++) {
            System.out.printf("C(%d) = %d\n", i, catalan(i));
        }

        System.out.println("\n=== 漢諾塔移動步數 ===");
        System.out.println("hanoi(3) = " + hanoi(3));
        System.out.println("hanoi(4) = " + hanoi(4));

        System.out.println("\n=== 回文數判斷 ===");
        int[] testNums = {12321, 12345, 1221, 10, 1};
        for (int n : testNums) {
            System.out.printf("%d 是回文數：%s\n", n, isPalindromeNumber(n));
        }
    }
}
