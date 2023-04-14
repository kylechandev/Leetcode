import java.math.BigDecimal;
import java.util.Arrays;

/*
 * @Author: kaic
 * @Date: 2022-11-13 09:17:53
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-01-08 12:37:29
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */

/**
 * 临时算法
 */
public class Test {

    /**
     * https://stackoverflow.com/questions/42381759/finding-first-quartile-and-third-quartile-in-integer-array-using-java
     */
    public static double[] Quartiles(double[] val) {
        double ans[] = new double[3];

        for (int quartileType = 1; quartileType < 4; quartileType++) {
            float length = val.length + 1;
            double quartile;
            float newArraySize = (length * ((float) (quartileType) * 25 / 100)) - 1;
            Arrays.sort(val);
            if (newArraySize % 1 == 0) {
                quartile = val[(int) (newArraySize)];
            } else {
                int newArraySize1 = (int) (newArraySize);
                quartile = (val[newArraySize1] + val[newArraySize1 + 1]) / 2;
            }
            ans[quartileType - 1] = quartile;
        }
        return ans;
    }

    public static void main(String[] args) {
        double[] temp = new double[] { 10.0, 13.0, 200.0, 25.0, 20.0, 12.0 };
        System.out.println(Arrays.toString(Quartiles(temp)));
    }
}
