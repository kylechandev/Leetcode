/*
 * @Author: kaic
 * @Date: 2022-11-07 11:35:37
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 20:55:17
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
public class MyQueue33 {

    private int[] array;
    private int front;
    private int rear;

    MyQueue33(int capacity) {
        this.array = new int[capacity];
    }

    public void enqueue(int data) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[rear] = data;
        rear = nextPoint(rear);
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int data = array[front];
        front = nextPoint(front);
        return data;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    private int nextPoint(int point) {
        return (point + 1) % array.length;
    }

    public void output() {
        for (int i = front; i != rear; i = nextPoint(i)) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(6);
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
