package leetcode.哈希表.简单;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 242. 有效的字母异位词
 * 
 * 简单
 * 
 * 
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * 
 * 
 * https://leetcode.cn/problems/valid-anagram/
 */
public class 有效的字母异位词 {

    /**
     * HashMap（哈希表 ）
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.getOrDefault(c, 0) < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 数组（也是哈希表）
     * 
     * 最优
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] array = new int[26];

        for (char c : s.toCharArray()) {
            array[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            array[c - 'a']--;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 排序
     */
    public static boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        Arrays.sort(sc);
        Arrays.sort(tc);

        System.out.println(Arrays.toString(sc));
        System.out.println(Arrays.toString(tc));

        return Arrays.equals(sc, tc);
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram3(s, t));
    }
}
