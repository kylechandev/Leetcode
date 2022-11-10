/*
 * @Author: kaic
 * @Date: 2022-11-10 22:25:36
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 22:36:58
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.用栈实现队列;

import java.util.Stack;

/**
 * 用栈实现队列
 * 
 * 思路：使用两个栈
 */
public class Solution {

    // 入队
    private static Stack<Integer> stackA = new Stack<Integer>();

    // 出队
    private static Stack<Integer> stackB = new Stack<Integer>();

    /**
     * 入队
     * 
     * 时间复杂度：O(1)
     */
    public static void push(int data) {
        stackA.push(data);
    }

    /**
     * 出队
     * 
     * 时间复杂度：
     * 如果涉及到A栈迁移到B栈，为：O(n)，否则为：O(1)
     */
    public static int pop() {
        // 先尝试用B栈出栈一个
        if (!stackB.isEmpty()) {
            return stackB.pop();
        }

        // 如果B栈为空了
        // 就把A栈中的元素全部出栈并入栈到B栈
        while (!stackA.isEmpty()) {
            stackB.push(stackA.pop());
        }

        // 然后B栈出栈第一个
        if (!stackB.isEmpty()) {
            return stackB.pop();
        }

        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        push(12);
        push(1);
        System.out.println(pop());
        push(2);
        push(24);
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }
}
