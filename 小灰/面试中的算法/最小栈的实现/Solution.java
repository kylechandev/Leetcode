/*
 * @Author: kaic
 * @Date: 2022-11-10 16:54:50
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-10 17:09:46
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.面试中的算法.最小栈的实现;

import java.util.Stack;

/**
 * 最小栈的实现
 * 
 * 要求：
 * 1、入栈
 * 2、出栈
 * 3、取最小元素
 * 
 * 并且这3个操作的时间复杂度都为O(1)，其中入栈和出栈本身就是O(1)，主要解决取最小元素的时间复杂度
 */
public class Solution {

    private static Stack<Integer> stack = new Stack<Integer>();
    // 解决思路：
    // 设置一个最小栈，stack入栈时将小值同时入栈到minStack中
    // 然后取最小值时，直接从minStack栈中弹出栈顶元素
    private static Stack<Integer> minStack = new Stack<Integer>();

    /**
     * 入栈操作
     */
    public static void push(int data) {
        stack.push(data);

        if (minStack.isEmpty()) {
            minStack.push(data);
        } else {
            int currentMin = minStack.peek();
            if (data < currentMin) {
                minStack.push(data);
            }
        }
    }

    /**
     * 出栈
     */
    public static int pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException();
        }

        int pop = stack.pop();
        if (pop <= minStack.peek()) {
            minStack.pop();
        }

        return pop;
    }

    /**
     * 获取最小元素
     */
    public static int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        push(1);
        push(2);
        push(3);

        System.out.println("获取最小值：" + getMin());
    }
}
