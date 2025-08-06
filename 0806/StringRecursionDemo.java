
public class StringRecursionDemo {

    public static boolean isPalindrome(String str, int start, int end) {
        if (start >= end) {
            return true;
        }
        System.out.printf("比較 str[%d]='%c' 與 str[%d]='%c'\n", start, str.charAt(start), end, str.charAt(end));
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return isPalindrome(str, start + 1, end - 1);
    }

    public static String reverseString(String str) {
        if (str.length() <= 1) {
            return str;
        }
        char lastChar = str.charAt(str.length() - 1);
        String restReversed = reverseString(str.substring(0, str.length() - 1));
        System.out.printf("反轉 '%s' = '%c' + 反轉('%s') = '%c' + '%s'\n",
                str, lastChar, str.substring(0, str.length() - 1), lastChar, restReversed);
        return lastChar + restReversed;
    }

    public static int stringLength(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }
        return 1 + stringLength(str, index + 1);
    }

    public static int countChar(String str, char target, int index) {
        if (index >= str.length()) {
            return 0;
        }
        int currentCount = (str.charAt(index) == target) ? 1 : 0;
        return currentCount + countChar(str, target, index + 1);
    }

    public static void main(String[] args) {
        String word1 = "racecar";
        String word2 = "hello";
        String word3 = "programming";

        System.out.println("=== 回文檢測 ===");
        System.out.printf("'%s' 是回文嗎？\n", word1);
        System.out.println("結果：" + isPalindrome(word1, 0, word1.length() - 1));

        System.out.printf("\n'%s' 是回文嗎？\n", word2);
        System.out.println("結果：" + isPalindrome(word2, 0, word2.length() - 1));

        System.out.println("\n=== 字串反轉 ===");
        System.out.printf("反轉 '%s'：\n", word2);
        System.out.println("最終結果：" + reverseString(word2));

        System.out.println("\n=== 其他字串操作 ===");
        System.out.printf("'%s' 的長度：%d\n", word3, stringLength(word3, 0));
        System.out.printf("'%s' 中字符 'r' 出現次數：%d\n", word3, countChar(word3, 'r', 0));
        System.out.printf("'%s' 中字符 'm' 出現次數：%d\n", word3, countChar(word3, 'm', 0));
    }
}
