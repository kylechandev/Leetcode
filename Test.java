/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-26 20:03:55
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */

/**
 * 临时算法
 */
public class Test {

    /**
     * https://stackoverflow.com/questions/42381759/finding-first-quartile-and-third-quartile-in-integer-array-using-java
     */
    public static int f(int n) {
        if (n < 2) {
            return n;
        }

        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        // 1 1 2 3 5 8
        System.out.println(f(6));
    }
}
