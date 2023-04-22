/*
 * @Author: kaic
 * @Date: 2022-10-30 23:04:30
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-19 10:03:51
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * 
 * 中等
 * 
 * https://leetcode.cn/problems/letter-case-permutation/
 * 
 * Created by kaic on 2022/10/30 23:06
 */
public class 字母大小写全排列 {

    private static List<String> list = new ArrayList<>();
    private static char[] arr;

    public static List<String> letterCasePermutation(String s) {
        arr = s.toCharArray();
        return dfs(0, new StringBuilder());
    }

    public static List<String> dfs(int index, StringBuilder sb) {
        // 若下标超出跳出
        if (index >= arr.length) {
            System.out.println("输出：" + sb.toString());
            list.add(sb.toString());
            return list;
        }

        // 继续遍历
        dfs(index + 1, new StringBuilder(sb).append(arr[index]));

        // 回溯的时候判断字母大小写
        if (arr[index] >= 'a') {
            dfs(index + 1, new StringBuilder(sb).append((char) (arr[index] - 32)));
        } else if (arr[index] >= 'A') {
            dfs(index + 1, new StringBuilder(sb).append((char) (arr[index] + 32)));
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }
}
