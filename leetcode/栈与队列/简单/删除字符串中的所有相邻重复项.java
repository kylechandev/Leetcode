/*
 * @Author: kaic
 * @Date: 2022-12-06 22:47:35
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-07 20:18:36
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.栈与队列.简单;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * 
 * 简单
 * 
 * 
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 
 * 
 * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/
 */
public class 删除字符串中的所有相邻重复项 {

    /**
     * 超时
     */
    public static String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder(s);

        boolean find = false;
        while (true) {
            find = false;
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == sb.charAt(i - 1)) {
                    find = true;
                    sb.delete(i - 1, i + 1);
                }
            }
            if (!find) {
                break;
            }
        }

        return sb.toString();
    }

    /**
     * 利用栈
     */
    public static String removeDuplicates2(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    /**
     * 双指针
     */
    public static String removeDuplicates3(String s) {
        char[] result = s.toCharArray();

        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            result[slow] = result[fast];

            if (slow > 0 && result[slow] == result[slow - 1]) {
                slow--;
            } else {
                slow++;
            }
        }

        return new String(result, 0, slow);
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates3(s));
    }
}
