
import java.util.*;

public class LC40_CombinationSum2_Procurement {

    static List<List<Integer>> res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        for (List<Integer> comb : res) {
            for (int i = 0; i < comb.size(); i++) {
                System.out.print((i > 0 ? " " : "") + comb.get(i));
            }
            System.out.println();
        }
    }

    static void backtrack(int[] c, int remain, int start, List<Integer> path) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < c.length; i++) {
            if (i > start && c[i] == c[i - 1]) {
                continue;
            }
            if (c[i] > remain) {
                break;
            }
            path.add(c[i]);
            backtrack(c, remain - c[i], i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
