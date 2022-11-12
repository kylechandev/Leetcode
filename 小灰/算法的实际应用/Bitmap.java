/*
 * @Author: kaic
 * @Date: 2022-11-12 14:08:55
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-12 15:16:04
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.算法的实际应用;

/**
 * 此处所指的位图：内存中连续的二进制位（bit）所组成的数据结构
 * 
 * 该算法(位图算法)主要用于对大量整数做去重和查询操作
 * 
 * JDK提供了BitSet实现位图算法
 * 
 * https://zhuanlan.zhihu.com/p/54783053
 */
public class Bitmap {

    private long[] words; // 一个word存64位
    private int size; // 表示总共的位数

    /**
     * 例如初始化传递size=128
     * 因为一个word存储64位，那么`long[] words`数组的长度为2，`int size`的大小为128
     */
    Bitmap(int size) {
        this.size = size;
        // `size - 1`表示从0开始
        this.words = new long[getWordIndex(size - 1) + 1]; // 1+1
    }

    /**
     * 判断Bitmap某一位的状态
     * 
     * @param bitIndex 位图的第bitIndex位
     */
    public boolean getBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException();
        }

        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    /**
     * 把Bitmap某一位设置为true
     * 
     * @param bitIndex 位图的第bitIndex位
     */
    public void setBit(int bitIndex) {
        if (bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException();
        }

        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }

    /**
     * 定位bitmap某一位所对应的word
     * 
     * @param bitIndex 位图的第bitIndex位
     */
    private int getWordIndex(int bitIndex) {
        // 右移6位，64位
        return bitIndex >> 6;
    }

    public static void main(String[] args) {
        Bitmap bitmap = new Bitmap(128);
        bitmap.setBit(20);
        System.out.println(bitmap.getBit(120));
        System.out.println(bitmap.getBit(20));
        System.out.println(bitmap.getBit(127));
    }
}
