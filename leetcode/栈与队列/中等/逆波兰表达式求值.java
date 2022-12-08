/*
 * @Author: kaic
 * @Date: 2022-12-07 20:19:54
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-07 20:42:19
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.中等;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 150. 逆波兰表达式求值（逆波兰表示`后缀表达式`） - 本题要求`后缀`转`中缀`
 * 
 * 中等
 * 
 * 
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 注意 两个整数之间的除法只保留整数部分。
 * 
 * 可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 
 * 
 * 【示例】
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 
 * 
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class 逆波兰表达式求值 {

    /**
     * 适合用栈操作运算：
     * 1、遇到数字则入栈；
     * 2、遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
     */
    public static int evalRPN(String[] tokens) {
        // 优先级：() */ +- 12

        Deque<String> stack = new ArrayDeque<>();

        for (String s : tokens) {
            if (isDigital(s)) {
                // 数字入栈
                stack.push(s);
            } else {
                // 运算符取出栈顶元素计算
                Integer right = Integer.parseInt(stack.pop());
                Integer left = Integer.parseInt(stack.pop());
                Integer result;
                if (s.equals("+")) {
                    result = left + right;
                } else if (s.equals("-")) {
                    result = left - right;
                } else if (s.equals("*")) {
                    result = left * right;
                } else if (s.equals("/")) {
                    result = (int) (left / ((double) right));
                } else {
                    throw new IllegalArgumentException("无法识别的运算符号");
                }
                stack.push(result.toString());
            }
        }

        if (stack.size() == 1) {
            return Integer.parseInt(stack.pop());
        } else {
            return -1;
        }
    }

    private static boolean isDigital(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String[] tokens = new String[] { "4", "13", "5", "/", "+" }; // ((2 + 1) * 3) = 9

        System.out.println(evalRPN(tokens));
    }
}
