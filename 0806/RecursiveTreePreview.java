
import java.util.*;

public class RecursiveTreePreview {

    static class FileNode {

        String name;
        boolean isFile;
        List<FileNode> children;

        public FileNode(String name, boolean isFile) {
            this.name = name;
            this.isFile = isFile;
            if (!isFile) {
                children = new ArrayList<>();
            }
        }
    }

    public static int countFiles(FileNode node) {
        if (node.isFile) {
            return 1;
        }
        int count = 0;
        for (FileNode child : node.children) {
            count += countFiles(child);
        }
        return count;
    }

    static class MenuItem {

        String title;
        List<MenuItem> subItems;

        public MenuItem(String title) {
            this.title = title;
            this.subItems = new ArrayList<>();
        }
    }

    public static void printMenu(MenuItem menu, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(menu.title);
        for (MenuItem sub : menu.subItems) {
            printMenu(sub, level + 1);
        }
    }

    public static List<Object> flattenNestedList(List<?> nestedList) {
        List<Object> flatList = new ArrayList<>();
        for (Object obj : nestedList) {
            if (obj instanceof List) {
                flatList.addAll(flattenNestedList((List<?>) obj));
            } else {
                flatList.add(obj);
            }
        }
        return flatList;
    }

    public static int maxDepth(List<?> nestedList) {
        int max = 1;
        for (Object obj : nestedList) {
            if (obj instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<?>) obj));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        FileNode root = new FileNode("root", false);
        FileNode folder1 = new FileNode("folder1", false);
        FileNode folder2 = new FileNode("folder2", false);
        folder1.children.add(new FileNode("file1.txt", true));
        folder1.children.add(new FileNode("file2.txt", true));
        folder2.children.add(new FileNode("file3.txt", true));
        root.children.add(folder1);
        root.children.add(folder2);
        root.children.add(new FileNode("file4.txt", true));

        System.out.println("檔案總數：" + countFiles(root));

        MenuItem mainMenu = new MenuItem("主選單");
        MenuItem sub1 = new MenuItem("檔案");
        MenuItem sub2 = new MenuItem("編輯");
        MenuItem sub11 = new MenuItem("新建");
        MenuItem sub12 = new MenuItem("打開");
        sub1.subItems.add(sub11);
        sub1.subItems.add(sub12);
        mainMenu.subItems.add(sub1);
        mainMenu.subItems.add(sub2);

        System.out.println("\n選單結構：");
        printMenu(mainMenu, 0);

        List<Object> nestedList = Arrays.asList(
                1,
                Arrays.asList(2, 3),
                Arrays.asList(Arrays.asList(4, 5), 6),
                7
        );

        System.out.println("\n展平後的清單：" + flattenNestedList(nestedList));
        System.out.println("巢狀清單最大深度：" + maxDepth(nestedList));
    }
}
