/*
 * @Author: kaic
 * @Date: 2022-11-08 22:40:56
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-10 20:27:52
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.排序算法;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * 快速排序 - 基于元素之间的比较来进行排序 - 不稳定
 * 
 * 平均时间复杂度：O(nlogn) -> 每一轮需要把数组所有元素都遍历一遍，遍历一共有logn轮
 * 最坏时间复杂度：O(n²)
 * 空间复杂度：O(logn) -> 递归栈
 * 
 * 
 * 【思想】
 * 每次取列表第一个元素作为基准值pivot，然后将大于基准值的所有值放在右侧，小于的放在左侧，分成两半，然后这两半再同理排序
 * 
 * 
 * 【存在问题】
 * 例如列表：8 7 6 5 4 3 2 1，每次取第一个值作为pivot，会导致无法将列表分为两半，时间复杂度直接退化成O(n²)
 * 
 * 【解决办法】
 * pivot值先随机取一个，然后和第一个值进行交换，最后再进行排序
 */
public class QuickSort {

    /**
     * 递归实现
     */
    public static void quickSort(int[] array, int startIndex, int endIndex) {
        if (array.length == 0 || startIndex >= endIndex) {
            // 递归结束条件
            return;
        }

        int partition = partition(array, startIndex, endIndex);
        quickSort(array, startIndex, partition - 1);
        quickSort(array, partition + 1, endIndex);
    }

    /**
     * 递归实现
     */
    public static void quickSortV2(int[] array, int startIndex, int endIndex) {
        if (array.length == 0 || startIndex >= endIndex) {
            // 递归结束条件
            return;
        }

        int partition = partitionV2(array, startIndex, endIndex);
        quickSortV2(array, startIndex, partition - 1);
        quickSortV2(array, partition + 1, endIndex);
    }

    /**
     * 栈实现
     */
    public static void quickSortByStack(int[] array, int startIndex, int endIndex) {
        // 1、构架一个栈
        Stack<Map<String, Integer>> stack = new Stack<>();

        // 2、入栈根数据
        final Map<String, Integer> map = Map.of("startIndex", startIndex, "endIndex", endIndex);
        stack.push(map);

        while (!stack.isEmpty()) {
            Map<String, Integer> param = stack.pop();

            int start = param.get("startIndex");
            int end = param.get("endIndex");

            int pivot = partitionV2(array, start, end);

            // 开始入栈 左 右 半部分
            if (start < pivot - 1) {
                Map<String, Integer> left = Map.of("startIndex", start, "endIndex", pivot - 1);
                stack.push(left);
            }

            if (pivot + 1 > end) {
                Map<String, Integer> right = Map.of("startIndex", pivot + 1, "endIndex", end);
                stack.push(right);
            }
        }
    }

    /**
     * 随机取一个基准值
     */
    private static int randomPivot(int[] array, int startIndex, int endIndex) {
        // [min, max] n=rand.nextInt(max-min+1)+min
        int randomIndex = new Random().nextInt(endIndex - startIndex + 1) + startIndex;

        // 交换到第一个元素所在的位置
        if (randomIndex != 0) {
            int temp = array[startIndex];
            array[startIndex] = array[randomIndex];
            array[randomIndex] = temp;
        }

        // 返回第一个元素作为基准值（这里已经是交换完成的随机值了）
        return array[startIndex];
    }

    /**
     * 分治（双边循环法）- 计算基准值下标
     * 
     * 逻辑更加直观、但代码量多
     * 
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * 
     * @return 返回中间下标
     */
    private static int partition(int[] array, int startIndex, int endIndex) {
        // 选定基准值
        int pivot = randomPivot(array, startIndex, endIndex);

        // 左右循环指针
        int left = startIndex;
        int right = endIndex;

        // while (left != right) {
        while (left < right) {

            // 1、右指针位置的值比pivot大时，right向左侧移动，直到找到右侧比pivot值小的数
            while (left < right && array[right] > pivot) {
                right--;
            }

            // 2、左指针位置的值比pivot小或等于时，left向右侧移动，直到直到左侧比pivot值大的数
            while (left < right && array[left] <= pivot) {
                left++;
            }

            // 3、交换left和right指针数据（把`左侧找到的比pivot值大的数`和`右侧找到的比pivot值小的数`交换，实现左侧都是小数，右侧都是大数）
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        // 4、左右指针重合后和pivot值交换位置
        // array[startIndex]的值就是pivot，array[left]现在在中间位置，交换两者位置让pivot值到中间去
        array[startIndex] = array[left];
        array[left] = pivot; // pivot就是array[startIndex]

        return left;
    }

    /**
     * 分治（单边循环法）- 计算基准值下标
     * 
     * 代码简洁
     * 
     * @param arr        待交换的数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     */
    private static int partitionV2(int[] array, int startIndex, int endIndex) {
        // 1、设置一个基准值
        int pivot = randomPivot(array, startIndex, endIndex);

        // 2、设置一个mark指针，表示小于基准值元素的区域边界下标
        int markIndex = startIndex;

        // 3、从基准值后一个元素开始遍历
        for (int i = startIndex + 1; i <= endIndex; i++) {
            // 4、找到比基准值小的元素
            if (array[i] < pivot) {
                // 5、让mark指针右移（找到比pivot值小的元素了，所以边界下标向后移动一个位置）
                markIndex++;

                // 6、并且和mark指针所在位置进行元素交换（目的是让比pivot小的元素全部移动到左侧）
                int temp = array[i];
                array[i] = array[markIndex];
                array[markIndex] = temp;
            }
        }

        // 7、最后让pivot和mark指针位置进行交换（目的是让pivot元素移动到二分中间）
        // mark指针最后所处的位置是：整个数组中顺序查看最后一个比pivot值小的的元素，这时候让它和第一个元素pivot值进行位置交换
        array[startIndex] = array[markIndex];
        array[markIndex] = pivot;

        return markIndex;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 3, 4, 2, 1, 5, 6, 7, 8 };
        System.out.println("原始数组：\n" + Arrays.toString(array));
        System.out.println();

        quickSort(array, 0, array.length - 1);
        System.out.println("排序数组V1：\n" + Arrays.toString(array));

        int[] array2 = new int[] { 3, 4, 2, 1, 5, 6, 7, 8 };
        quickSortV2(array2, 0, array2.length - 1);
        System.out.println("排序数组V2：\n" + Arrays.toString(array2));

        int[] array3 = new int[] { 3, 4, 2, 1, 5, 6, 7, 8 };
        quickSortByStack(array3, 0, array3.length - 1);
        System.out.println("排序数组Stack：\n" + Arrays.toString(array3));
    }
}
