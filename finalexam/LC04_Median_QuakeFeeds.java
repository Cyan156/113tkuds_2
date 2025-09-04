
import java.util.*;

public class LC04_Median_QuakeFeeds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            B[j] = sc.nextDouble();
        }
        if (n > m) {
            double[] tmp = A;
            A = B;
            B = tmp;
            int t = n;
            n = m;
            m = t;
        }
        int imin = 0, imax = n, half = (n + m + 1) / 2;
        double median = 0;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half - i;
            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];
            if (Aleft <= Bright && Bleft <= Aright) {
                if ((n + m) % 2 == 1) {
                    median = Math.max(Aleft, Bleft);
                } else {
                    median = (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                }
                break;
            } else if (Aleft > Bright) {
                imax = i - 1;
            } else {
                imin = i + 1;
            }
        }
        System.out.printf("%.1f\n", median);
    }
}
