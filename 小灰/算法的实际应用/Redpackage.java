/*
 * @Author: kaic
 * @Date: 2022-11-12 18:02:27
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-12 21:36:31
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.算法的实际应用;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 红包拆分算法
 * 
 * https://www.51cto.com/article/690799.html
 */
public class Redpackage {

    /**
     * 拆分红包 - 二倍均值法
     * 
     * 每次随机金额的上限定为剩余人均金额的2倍
     * 
     * @param totalAmount    总金额（以分为单位）
     * @param totalPeopleNum 总人数
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> result = new ArrayList<>();

        Random random = new Random();

        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;

        for (int i = 0; i < totalPeopleNum - 1; i++) { // 随机分出totalPeopleNum-1个红包
            // [min, max] n=rand.nextInt(max-min+1)+min
            // min 1
            // max restAmount / restPeopleNum * 2 - 1
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1 - 1 + 1) + 1;
            result.add(amount);

            // 剩余金额和人数递减
            restAmount -= amount;
            restPeopleNum--;
        }
        // 最后一份剩下的金额
        result.add(restAmount);

        int total = 0;
        for (int i = 0; i < result.size(); i++) {
            total += result.get(i);
        }
        System.out.println("红包总额：" + total);

        return result;
    }

    public static void main(String[] args) {
        Integer totalAmount = 10;
        Integer totalPeopleNum = 10;

        System.out.println("红包1：" + divideRedPackage(totalAmount, totalPeopleNum));
    }
}
