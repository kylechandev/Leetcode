/*
 * @Author: kaic
 * @Date: 2022-11-06 15:09:10
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 20:55:06
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.数组;

/**
 * 数组
 * 
 * 适合：【读操作多、写操作少】的场景
 */
public class MyArray {

    // 数组
    private int[] array;
    // 当前数组中实际元素的数量
    private int size;

    MyArray(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 指定位置插入值
     * 
     * @param index   插入的位置
     * @param element 插入的值
     */
    public void insert(int index, int element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index is illegal!");
        }

        while (index >= array.length) {
            // 需要扩容
            resize();
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
     * 数组扩容
     */
    private void resize() {
        // 创建一个新的两倍大小的数组
        int[] newArray = new int[array.length * 2];
        // 将当前数组的内容复制到新数组中
        System.arraycopy(array, 0, newArray, 0, array.length);
        // 更新数组引用
        array = newArray;
    }

    /**
     * 删除数组指定位置的元素
     * 
     * @param index 指定的要删除的位置
     * 
     * @return 删除的元素
     */
    public int delete(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        int deleted = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        size--;

        return deleted;
    }

    /**
     * 输出数组
     */
    public void output() {
        System.out.print("输出数组：");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray(5);
        myArray.insert(0, 3);
        myArray.insert(1, 7);
        myArray.insert(2, 9);
        myArray.insert(3, 5);
        myArray.insert(1, 6);
        myArray.insert(5, 8);
        myArray.output();
        System.out.println("删除元素：" + myArray.delete(3));
        myArray.output();
    }
}
