
public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        int sum = 0;
        int max = scores[0];
        int min = scores[0];

        for (int score : scores) {
            sum += score;
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
        }

        double average = (double) sum / scores.length;

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            if (score >= 90) {
                countA++; 
            }else if (score >= 80) {
                countB++; 
            }else if (score >= 70) {
                countC++; 
            }else if (score >= 60) {
                countD++; 
            }else {
                countF++;
            }
        }

        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > average) {
                aboveAverageCount++;
            }
        }

        System.out.println("成績報表：");
        System.out.println("成績列表：" + java.util.Arrays.toString(scores));
        System.out.printf("平均分數：%.2f\n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
        System.out.println("等第人數統計：");
        System.out.println("A: " + countA);
        System.out.println("B: " + countB);
        System.out.println("C: " + countC);
        System.out.println("D: " + countD);
        System.out.println("F: " + countF);
        System.out.println("高於平均分的學生人數：" + aboveAverageCount);
    }
}
