/*
 * @Author: kaic
 * @Date: 2022-12-06 22:23:12
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-19 09:46:22
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.简单;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 225. 用队列实现栈 - 两个队列实现栈
 * 
 * 简单
 * 
 * 
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 
 * 
 * https://leetcode.cn/problems/implement-stack-using-queues/
 */
public class 用队列实现栈 {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(33);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class MyStack {

    // 和栈中元素保持一致 - 主队列
    private Queue<Integer> queue1 = new ArrayDeque<>();
    // 辅助队列 - 只用在处理入队列
    private Queue<Integer> queue2 = new ArrayDeque<>();

    public MyStack() {
    }

    public void push(int x) {
        // 「先往辅助队列里面塞」
        queue2.offer(x);

        // 1
        // queue2: 1 queue1: empty
        // queue2: empty queeue1: 1
        // 2
        // queue2: 2 queue1: 1
        // queue2: 2 1 queue1: empty
        // queue2: empty queue1: 2 1

        // 「然后再把主队列的数据全部出列到辅助队列，实现反转形成栈的特性」
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }

        // 「最后交换主/辅助队列」
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp; // 此时queue2辅助队列又变成了空的
    }

    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
