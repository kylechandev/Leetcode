/*
 * @Author: kaic
 * @Date: 2022-11-07 11:22:09
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-05-09 09:26:37
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
public class MyQueue<T> {

    // 队列
    private Object[] array;
    // 对头位置
    private int front;
    // 队尾位置
    private int rear;

    MyQueue(int capacity) {
        this.array = new Object[capacity];
    }

    /**
     * 入队
     * 
     * @param data 入队数据
     */
    public void offer(T data) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[rear] = data;
        rear = nextPoint(rear);
    }

    /**
     * 出队
     */
    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        T data = (T) array[front];
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
        MyQueue<Integer> myQueue = new MyQueue<>(6);
        myQueue.offer(3);
        myQueue.offer(5);
        myQueue.offer(6);
        myQueue.offer(8);
        myQueue.offer(1);
        myQueue.poll();
        myQueue.poll();
        myQueue.poll();
        myQueue.offer(2);
        myQueue.offer(4);
        myQueue.output();
    }
}
