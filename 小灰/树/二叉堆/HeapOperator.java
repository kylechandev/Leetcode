package 小灰.树.二叉堆;

import java.util.Arrays;

/**
 * 二叉堆
 * 
 * 本质上是一种完全二叉树
 * 
 * 分为两种类型：
 * 
 * 【最大堆】
 * 任何一个父节点的值，都大于或等于它左、右孩子 节点的值
 * 
 * 【最小堆】
 * 任何一个父节点的值，都小于或等于它左、右孩子 节点的值
 * 
 * 根节点叫做【堆顶】
 * 
 * 
 * 【操作】
 * 1、构建二叉堆 -> 把一个无序的完全二叉树调整为二叉堆
 * 2、插入节点 ->【永远是在最后一个位置插入，然后进行`上浮`操作，使插入的节点到正确的位置】
 * 3、删除节点 ->【永远是删除堆顶节点（然后将堆的最后一个节点替换到原本栈顶的位置），然后进行`下浮`操作，使删除节点后的二叉堆维持结构】
 */
public class HeapOperator {

    /**
     * 上浮调整
     * 
     * `叶子节点(子节点)`上浮
     * 
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = getParentIndex(childIndex);

        int tempChild = array[childIndex];

        // 开始上浮
        while (childIndex > 0 && tempChild < array[parentIndex]) {
            array[childIndex] = array[parentIndex];

            // 更新父节点和子节点的位置
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }

        array[childIndex] = tempChild;
    }

    /**
     * 下浮调整
     * 
     * `非叶子节点(父节点)`下浮
     * 
     * @param array       待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // 临时变量，用来记录当前的父节点数据
        int tempParent = array[parentIndex];
        // 左孩子
        int childIndex = parentIndex * 2 + 1;

        // 开始比较
        while (childIndex < length) {
            // 如果存在右孩子，并且右孩子大于左孩子，那么使用右孩子作为比较交换对象
            if (childIndex + 1 < length && array[childIndex] > array[childIndex + 1]) {
                childIndex++;
            }

            if (tempParent <= array[childIndex]) {
                // 父节点小于子节点，那么无需进行下沉
                break;
            }

            // 下沉
            array[parentIndex] = array[childIndex];

            // 更新parent和child，继续判断下面的节点
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }

        // 最后更新parent
        array[parentIndex] = tempParent;
    }

    /**
     * 构建堆
     * 
     * 构建堆的过程就是一个不断`下沉`的过程（从最后一个非叶子节点开始，下沉的对象是`非叶子节点`） 时间复杂度为 O(n)
     * 
     * 还有一种构建方式（不推荐）：将已知数组一个一个加入到堆中 ====> Time = O(nlogn)
     * 
     * @param array 待调整的堆
     */
    public static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始，依次下沉调整

        // getParentIndex(array.length - 1)
        // 找到最后一个叶子节点`array.length-1`的父节点
        for (int i = getParentIndex(array.length - 1); i >= 0; i--) {
            downAdjust(array, i, array.length);
        }
    }

    /**
     * 获取子节点的父节点所在位置
     * 
     * @param childIndex 子节点位置
     */
    private static int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public static void main(String[] args) {
        // 二叉堆虽然是一个完全二叉树，但它 的存储方式并不是链式存储，而是顺序存储。
        // 换句话说，二叉堆的所有节点都存储在 数组 中
        int[] array = new int[] { 1, 3, 2, 6, 5, 7, 8, 9, 10, 0 };
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 7, 1, 3, 10, 5, 2, 8, 9, 6 };
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
