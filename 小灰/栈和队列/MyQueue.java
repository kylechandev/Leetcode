/*
 * @Author: kaic
 * @Date: 2022-11-07 11:22:09
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 20:55:10
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.栈和队列;

/**
 * 队列
 * 
 * 数组实现（循环队列）
 * 
 * 入队列
 * 出队列
 * 
 * 判断队列是否已经满
 * 判断队列是否为空
 */
public class MyQueue {

    // 队列
    private int[] array;
    // 对头位置
    private int front;
    // 队尾位置
    private int rear;

    MyQueue(int capacity) {
        this.array = new int[capacity];
    }

    /**
     * 入队
     * 
     * @param data 入队数据
     */
    public void enqueue(int data) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[rear] = data;
        rear = nextPoint(rear);
    }

    /**
     * 出队
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int data = array[front];
        front = nextPoint(front);
        return data;
    }

    private int nextPoint(int point) {
        return (point + 1) % array.length;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否已经满
     */
    public boolean isFull() {
        return nextPoint(rear) == front;
    }

    public void output() {
        for (int i = front; i != rear; i = nextPoint(i)) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(6);
        myQueue.enqueue(3);
        myQueue.enqueue(5);
        myQueue.enqueue(6);
        myQueue.enqueue(8);
        myQueue.enqueue(1);
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.enqueue(2);
        myQueue.enqueue(4);
        myQueue.output();
    }
}
