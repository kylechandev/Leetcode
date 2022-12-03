/*
 * @Author: kaic
 * @Date: 2022-12-03 13:00:36
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-03 13:14:00
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.哈希表.简单;

import java.util.ArrayList;
import java.util.List;

/**
 * 383. 赎金信
 * 
 * 简单
 * 
 * 
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 
 * 如果可以，返回 true ；否则返回 false 。
 * 
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * 
 * 
 * https://leetcode.cn/problems/ransom-note/
 */
public class 赎金信 {

    /**
     * 暴力
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        List<Character> list = new ArrayList<>();
        for (Character c : ransomNote.toCharArray()) {
            list.add(c);
        }
        List<Character> set2 = new ArrayList<>();
        for (Character c : magazine.toCharArray()) {
            set2.add(c);
        }

        for (Character c : list) {
            if (set2.contains(c)) {
                set2.remove(c);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 哈希
     */
    public static boolean canConstruct2(String ransomNote, String magazine) {
        int[] record = new int[26];

        // 把字典的每个字符存起来
        for (char c : magazine.toCharArray()) {
            record[c - 'a'] += 1;
        }

        // 用字典的每个字符去还原原文
        for (char c : ransomNote.toCharArray()) {
            record[c - 'a'] -= 1;
        }

        for (int i : record) {
            // 如果某个字符的出现/使用次数小于0了，说明字典中缺少还原原文的字符，意味着magazine不能还原为ransomNote
            if (i < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";

        System.out.println(canConstruct2(ransomNote, magazine));
    }
}
