/*
 * @Author: kaic
 * @Date: 2022-11-08 17:00:38
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 20:53:43
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.树.优先队列;

import java.util.Arrays;

/**
 * 优先队列 - 基于二叉堆实现
 * 
 * 用最大堆来实现最大优先队列
 * 
 * 每一次入队操作就是堆的`插入`操作，每一次出队操作就是`删除`堆顶节点。
 */
public class PriorityQueue {

    // 队列数组
    private int[] array;
    // 当前队列实际数据数量
    private int size;

    PriorityQueue() {
        // 默认容量为32
        this(32);
    }

    PriorityQueue(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 入队列
     * 
     * @param data 值
     */
    public void enqueue(int data) {
        if (isFull()) {
            // 自动扩容
            resize();
        }

        // 将数据添加到 二叉堆 的最后一个节点
        array[size++] = data;

        // 开始上浮
        upAdjust();
    }

    /**
     * 出队列
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty!");
        }

        // 出堆顶数据
        int data = array[0];

        // 将最后一个节点替换到第一个节点的位置
        array[0] = array[--size];

        // 开始下浮
        downAdjust();

        return data;
    }

    /**
     * 上浮
     */
    private void upAdjust() {
        int childIndex = size - 1;
        int parentIndex = getParentIndex(childIndex);

        // 准备上浮这个子节点
        int tempChild = array[childIndex];

        // 开始上浮
        while (childIndex > 0 && tempChild > array[parentIndex]) {
            // 当子节点位置有效时 并且 子节点的值大于父节点的值
            // 将子节点上浮到父节点的位置，父节点转移到子节点的位置
            array[childIndex] = array[parentIndex];

            // 更新子节点和父节点
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
        }

        // 浮动完成
        array[childIndex] = tempChild;
    }

    /**
     * 下浮
     */
    private void downAdjust() {
        int parentIndex = 0;
        int childIndex = getLeftChildIndex(parentIndex);

        int tempParent = array[0];

        // 开始下浮
        while (childIndex < size) {
            // 左子节点有效
            // 如果右子节点存在，并且值大于左子节点，那么向右子节点下浮
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }

            if (tempParent >= array[childIndex]) {
                // 不用下浮了
                break;
            }

            // 将父节点下浮到子节点的位置，子节点转移到父节点的位置
            array[parentIndex] = array[childIndex];

            // 更新位置
            parentIndex = childIndex;
            childIndex = getLeftChildIndex(parentIndex);
        }

        // 完成下浮
        array[parentIndex] = tempParent;
    }

    /**
     * 获取子节点的父节点位置
     * 
     * @param childIndex 子节点的位置
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * 获取父节点的左子节点位置
     * 
     * @param parentIndex 父节点的位置
     */
    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    /**
     * 判断队列是否为空
     */
    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断队列是否已满
     */
    private boolean isFull() {
        return size >= array.length;
    }

    /**
     * 扩容
     */
    private void resize() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enqueue(12);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(24);

        System.out.println("出队列：" + priorityQueue.dequeue());
        System.out.println("出队列：" + priorityQueue.dequeue());
        System.out.println("出队列：" + priorityQueue.dequeue());
    }
}
