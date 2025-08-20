
import java.util.*;

public class M11_HeapSortWithTie {

    static class Student {

        int score, index;

        Student(int s, int i) {
            score = s;
            index = i;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        String[] parts = sc.nextLine().trim().split("\\s+");
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Student(Integer.parseInt(parts[i]), i);
        }

        heapSort(arr);

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i].score);
        }
        System.out.println();
    }

    static void heapSort(Student[] arr) {
        int n = arr.length;
        // Build Max Heap (bottom-up)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            Student tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapify(arr, i, 0);
        }
    }

    static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && compare(arr[l], arr[largest]) > 0) {
            largest = l;
        }
        if (r < n && compare(arr[r], arr[largest]) > 0) {
            largest = r;
        }
        if (largest != i) {
            Student tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;
            heapify(arr, n, largest);
        }
    }

    static int compare(Student a, Student b) {
        if (a.score != b.score) {
            return a.score - b.score;
        }
        return b.index - a.index; // Max-Heap: 較早輸入的 index 優先
    }
}
