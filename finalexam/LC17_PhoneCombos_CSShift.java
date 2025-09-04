
import java.util.*;

public class LC17_PhoneCombos_CSShift {

    static String[] map = {
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs", // 7
        "tuv", // 8
        "wxyz" // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        if (!digits.isEmpty()) {
            backtrack(digits, 0, new StringBuilder());
        }
    }

    static void backtrack(String digits, int index, StringBuilder sb) {
        if (index == digits.length()) {
            System.out.println(sb.toString());
            return;
        }
        String letters = map[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack(digits, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
