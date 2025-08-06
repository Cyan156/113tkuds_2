
import java.util.Arrays;

public class SelectionSortImplementation {

    public static void selectionSort(int[] array) {
        int n = array.length;
        int totalComparisons = 0;
        int totalSwaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            System.out.printf("第 %d 輪排序開始：\n", i + 1);

            for (int j = i + 1; j < n; j++) {
                totalComparisons++;
                System.out.printf("比較 array[%d]=%d 與 array[%d]=%d\n", j, array[j], minIndex, array[minIndex]);
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                totalSwaps++;
                System.out.printf("交換 array[%d]=%d 與 array[%d]=%d\n", i, array[minIndex], minIndex, temp);
            } else {
                System.out.println("本輪無需交換");
            }

            System.out.println("目前陣列狀態：" + Arrays.toString(array));
            System.out.println();
        }

        System.out.printf("排序完成！總比較次數：%d，總交換次數：%d\n", totalComparisons, totalSwaps);
    }

    public static void main(String[] args) {
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("原始陣列：" + Arrays.toString(numbers));
        selectionSort(numbers);
        System.out.println("排序結果：" + Arrays.toString(numbers));

        // 效能比較：使用氣泡排序（簡單版本）
        int[] bubbleArr = {64, 34, 25, 12, 22, 11, 90};
        int bubbleComparisons = 0;
        int bubbleSwaps = 0;
        int n = bubbleArr.length;

        System.out.println("\n氣泡排序效能比較：");
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                bubbleComparisons++;
                if (bubbleArr[j] > bubbleArr[j + 1]) {
                    int temp = bubbleArr[j];
                    bubbleArr[j] = bubbleArr[j + 1];
                    bubbleArr[j + 1] = temp;
                    bubbleSwaps++;
                }
            }
        }

        System.out.printf("氣泡排序比較次數：%d，交換次數：%d\n", bubbleComparisons, bubbleSwaps);
    }
}
