
import java.util.HashSet;
import java.util.Set;

public class AdvancedStringRecursion {

    // 產生字串所有排列組合 (全排列)
    public static void permute(String str, String prefix, Set<String> results) {
        if (str.length() == 0) {
            results.add(prefix);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(str.substring(0, i) + str.substring(i + 1), prefix + str.charAt(i), results);
        }
    }

    // 遞迴字串匹配（判斷 pattern 是否為 text 的子串）
    public static boolean isMatch(String text, String pattern, int tIndex, int pIndex) {
        if (pIndex == pattern.length()) {
            return true;
        }
        if (tIndex == text.length()) {
            return false;
        }
        if (text.charAt(tIndex) == pattern.charAt(pIndex)) {
            return isMatch(text, pattern, tIndex + 1, pIndex + 1);
        } else {
            return isMatch(text, pattern, tIndex + 1, pIndex);
        }
    }

    // 遞迴移除字串中重複字符 (保持第一次出現)
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, 0, new HashSet<>());
    }

    private static String removeDuplicatesHelper(String str, int index, Set<Character> seen) {
        if (index == str.length()) {
            return "";
        }
        char current = str.charAt(index);
        if (seen.contains(current)) {
            return removeDuplicatesHelper(str, index + 1, seen);
        } else {
            seen.add(current);
            return current + removeDuplicatesHelper(str, index + 1, seen);
        }
    }

    // 遞迴計算字串所有子字串組合 (不重複)
    public static void allSubstrings(String str, int start, int end, Set<String> results) {
        if (end == str.length() + 1) {
            return;
        } else if (start == end) {
            allSubstrings(str, 0, end + 1, results);
        } else {
            results.add(str.substring(start, end));
            allSubstrings(str, start + 1, end, results);
        }
    }

    public static void main(String[] args) {
        String testStr = "abc";

        System.out.println("=== 所有排列組合 ===");
        Set<String> permutations = new HashSet<>();
        permute(testStr, "", permutations);
        System.out.println(permutations);

        System.out.println("\n=== 字串匹配 ===");
        System.out.println("是否包含 'bc': " + isMatch(testStr, "bc", 0, 0));
        System.out.println("是否包含 'ac': " + isMatch(testStr, "ac", 0, 0));
        System.out.println("是否包含 'bd': " + isMatch(testStr, "bd", 0, 0));

        System.out.println("\n=== 移除重複字符 ===");
        String dupStr = "aabbccddeff";
        System.out.println("原字串：" + dupStr);
        System.out.println("移除重複後：" + removeDuplicates(dupStr));

        System.out.println("\n=== 所有子字串組合 ===");
        Set<String> substrings = new HashSet<>();
        allSubstrings(testStr, 0, 1, substrings);
        System.out.println(substrings);
    }
}
