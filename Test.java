import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-12-03 21:41:16
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */

/**
 * 临时算法
 */
public class Test {

    public static void test() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.println(list);

        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);
        unmodifiableList.add(3);
        System.out.println(unmodifiableList);
    }

    public static void main(String[] args) {
        test();
    }
}
