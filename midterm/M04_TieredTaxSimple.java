
import java.util.*;

public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        long totalTax = 0;

        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(sc.nextLine().trim());
            long tax = calcTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }

        System.out.println("Average: " + (totalTax / n));
    }

    static long calcTax(long income) {
        long tax = 0;
        long remain = income;

        // 0–120000 → 5%
        long tier = Math.min(remain, 120000);
        tax += tier * 5 / 100;
        remain -= tier;
        if (remain <= 0) {
            return tax;
        }

        // 120001–500000 → 12%
        tier = Math.min(remain, 500000 - 120000);
        tax += tier * 12 / 100;
        remain -= tier;
        if (remain <= 0) {
            return tax;
        }

        // 500001–1000000 → 20%
        tier = Math.min(remain, 1000000 - 500000);
        tax += tier * 20 / 100;
        remain -= tier;
        if (remain <= 0) {
            return tax;
        }

        // 1000001 以上 → 30%
        tax += remain * 30 / 100;

        return tax;
    }
}
