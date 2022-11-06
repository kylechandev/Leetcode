package 小灰.数组;

public class MyArray {

    // 数组
    private int[] array;
    // 当前大小
    private int size;

    MyArray(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 插入值
     */
    public void insert(int element, int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("over size!");
        }

        if (index < size) {
            // 后移
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
        }

        // 插入值
        array[index] = element;

        // 当前数组大小增加
        size++;
    }

    /**
     * 输出数组
     */
    public void output() {
        System.out.print("输出数组：");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray(10);
        myArray.insert(12, 0);
        myArray.output();
    }
}
