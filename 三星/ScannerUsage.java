/*
 * @Author: kaic
 * @Date: 2023-04-25 08:57:09
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2023-04-25 09:10:43
 * Copyright (c) 2023 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 三星;

import java.util.Scanner;

public class ScannerUsage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt());

        // ex: abc aa bb
        // next -> abc
        // nextLine -> abc aa bb

        scanner.close();
    }
}
