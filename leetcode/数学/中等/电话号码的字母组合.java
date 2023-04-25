/*
 * @Author: kaic
 * @Date: 2023-04-25 09:34:02
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-25 10:08:47
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.数学.中等;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合 - 回溯，子集型问题（子集型回溯）
 * 
 * 中等
 * 
 * 
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 
 * 示例：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 
 * 
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class 电话号码的字母组合 {

    private static final String[] MAPPING = new String[] {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    private final List<String> ans = new ArrayList<>();
    private char[] digits, path;

    public List<String> letterCombinations(String digits) {

        int n = digits.length();
        if (n == 0) {
            return ans;
        }

        this.digits = digits.toCharArray();
        path = new char[n];

        dfs(0);

        return ans;
    }

    private void dfs(int i) {
        // 边界条件，遍历完成
        if (i == digits.length) {
            ans.add(new String(path));
            return;
        }

        for (char c : MAPPING[digits[i] - '0'].toCharArray()) { // 遍历数字对应的 字母组合
            path[i] = c;
            dfs(i + 1);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(new 电话号码的字母组合().letterCombinations(digits));
    }
}
