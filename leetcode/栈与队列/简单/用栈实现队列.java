/*
 * @Author: kaic
 * @Date: 2022-12-06 22:09:48
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-19 09:36:04
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.简单;

import java.util.Stack;

/**
 * 232. 用栈实现队列 - 两个栈实现队列
 * 
 * 简单
 * 
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class 用栈实现队列 {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(33);
        queue.push(3);
        System.out.println(queue.pop());
        System.out.println(queue.pop());
    }
}

class MyQueue {

    // 负责入队
    private Stack<Integer> stackIn = new Stack<Integer>();
    // 负责出队
    private Stack<Integer> stackOut = new Stack<Integer>();

    public MyQueue() {
    }

    /**
     * 将元素 x 推到队列的末尾
     */
    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        dumpStackA();
        return stackOut.pop();
    }

    public int peek() {
        dumpStackA();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    private void dumpStackA() {
        // 每次 pop 或 peek 时，
        // 若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈，
        // 这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序。
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
    }
}
