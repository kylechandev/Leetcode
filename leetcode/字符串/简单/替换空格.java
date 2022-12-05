/*
 * @Author: kaic
 * @Date: 2022-12-05 14:46:38
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-05 14:54:33
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package leetcode.字符串.简单;

/**
 * 剑指 Offer 05. 替换空格
 * 
 * 简单
 * 
 * 
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 
 * 
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
 */
public class 替换空格 {

    /**
     * 直接调库函数
     */
    public static String replaceSpace(String s) {
        // `replaceAll`要比`replace`耗时更多
        return s.replace(" ", "%20"); // 0ms
        // return s.replaceAll(" ", "%20"); // 2ms
    }

    public static String replaceSpace2(String s) {
        // 创建一个char[]，大小为s.size * 3（因为一个`空格`替换为`%20`膨胀了3倍）
        // 遍历s，定义size=0，s[size++]=c，遇到空格则添加三次`%` `2` `0`
        return s.replace(" ", "%20");
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }
}
