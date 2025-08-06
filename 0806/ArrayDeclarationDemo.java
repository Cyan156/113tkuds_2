
public class ArrayDeclarationDemo {

    public static void main(String[] args) {
        int[] scores = {70, 80, 90, 100, 60};

        System.out.println("原始分數：");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("第 " + (i + 1) + " 位同學分數：" + scores[i]);
        }

        scores[2] = 95;
        scores[4] += 5;

        System.out.println("\n修改後的分數：");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("第 " + (i + 1) + " 位同學分數：" + scores[i]);
        }
    }
}
