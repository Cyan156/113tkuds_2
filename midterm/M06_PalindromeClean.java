
import java.util.*;

public class M06_PalindromeClean {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        int l = 0, r = s.length() - 1;
        boolean ok = true;

        while (l < r) {
            char cl = s.charAt(l);
            char cr = s.charAt(r);

            if (!Character.isLetter(cl)) {
                l++;
                continue;
            }
            if (!Character.isLetter(cr)) {
                r--;
                continue;
            }

            if (Character.toLowerCase(cl) != Character.toLowerCase(cr)) {
                ok = false;
                break;
            }
            l++;
            r--;
        }

        System.out.println(ok ? "Yes" : "No");
    }
}
