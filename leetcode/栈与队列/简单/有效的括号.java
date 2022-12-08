/*
 * @Author: kaic
 * @Date: 2022-12-06 22:39:39
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-06 22:45:59
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.简单;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20.有效的括号
 * 
 * 简单
 * 
 * 
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * 
 * 
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class 有效的括号 {

    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char pre = stack.pop();
                if (c == ')' && pre != '(') {
                    return false;
                } else if (c == '}' && pre != '{') {
                    return false;
                } else if (c == ']' && pre != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(())";
        System.out.println(isValid(s));
    }
}
