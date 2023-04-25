package 三星;

import java.io.FileReader;
import java.util.Scanner;

/**
 * 三星机试模拟器
 * 
 * DFS
 * 
 * https://blog.csdn.net/weixin_42688573/article/details/124965032
 */
public class TheFellowshipOfRheRing {

    /*
     * 全局变量记得每次重置！！！
     */

    // 每组测试用例中有 N 组兽人
    public static int N;

    // 每组测试数据的计算得到的最终的`最少金额`结果
    public static int finalMoney;

    // 兽人信息（每次遭遇的兽人数量 和 对应的过路费）
    public static int orcInfo[][] = new int[1000][2];

    // battle[i]为能打i场的人数
    // 题目要求：兽人有3点耐久，经过3次战斗后，兽人将会解散
    public static int battle[] = new int[3];

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(
                new FileReader(
                        "/Users/kchankc/WebstormProjects/leetcode/三星/TestCase/TheFellowshipOfRheRingTest.txt"));

        // 输入总的测试用例个数
        int T = in.nextInt();
        System.out.println("Input:");
        System.out.println("T: " + T);

        // 读取每个测试用例的数据
        for (int i = 0; i < T; i++) { // for循环 T 个测试用例
            N = in.nextInt(); // 当前测试用例中有 N 组兽人
            System.out.println("N: " + N);

            // 读取每组测试用例中的 兽人数量 和对应的 过路费
            for (int j = 0; j < N; j++) {
                orcInfo[j][0] = in.nextInt();
                orcInfo[j][1] = in.nextInt();
            }

            // clean data
            finalMoney = 0;

            // 开始计算
            dfs(0, 0);

            System.out.println("Output:");
            System.out.println("#" + (i + 1) + " " + finalMoney);

        }
    }

    /**
     * 整个过程类似 数的组合 情况
     * 
     * @param nowMoney 当前的总花费
     * @param passTime 已通过的兽人波数
     */
    private static void dfs(int nowMoney, int passTime) {
        // N组兽人全部对付过了
        if (passTime == N) {
            // System.out.println();
            // System.out.println("_____________nowMoney = " + nowMoney);
            // 到目前为止的花费比历史对付策略的花费小，或者，最终的花费没改过，因为是第一次搜到终点。
            if (nowMoney < finalMoney || finalMoney == 0) {
                finalMoney = nowMoney;
            }

            return;
        }

        // 剪枝
        if (nowMoney >= finalMoney && finalMoney != 0) {
            return;
        }

        int next_money = 0;
        for (int i = 0; i < 3; i++) {
            // 即将对付pass_time组兽人
            // 有三种情况
            // 第一种：直接付钱走人，即流程：此次的花费增加，通过这组兽人->对付下一组兽人
            if (i == 0) {
                // System.out.print("付钱 ");
                next_money = nowMoney + orcInfo[passTime][1];
                dfs(next_money, passTime + 1);
                continue;
            }
            // 第二种：雇佣兽人为自己的士兵，即流程：此次花费增加，两倍toll->对付下一组兽人
            if (i == 1) {
                // System.out.print("雇佣 ");
                next_money = nowMoney + orcInfo[passTime][1] * 2;
                int p = orcInfo[passTime][0];
                battle[0] += p;
                dfs(next_money, passTime + 1);
                battle[0] -= p;
                continue;
            }
            // 第三种：打仗
            if (i == 2) {
                // totalSoldier 表示所有的士兵人数
                int totalSoldier = battle[0] + battle[1] + battle[2];
                // totalOrc 表示 该组兽人的人数
                int totalOrc = orcInfo[passTime][0];
                // 第一种：人数够打
                if (totalSoldier >= totalOrc) {
                    // 为了回溯，暂存三组数值
                    int temp2 = battle[2];
                    int temp1 = battle[1];
                    int temp0 = battle[0];

                    // 战斗，可知战斗次数多的必是先雇佣的，优先消耗
                    int k = 2; // 从战斗数只有一个的开始战斗
                    // int leftOrc = totalOrc;
                    // totalOrc > 0表示剩下的兽人的数量大于零，只有剩下的兽人没有了，才会停止while
                    /*
                     * while (totalOrc > 0) {
                     * // 战斗数最高的先战斗，先减去总兽人数
                     * battle[k] -= totalOrc;
                     * // 此时的totalOrc表示剩下的兽人数
                     * // battle[k]为记录的上一步所剩下的雇佣兵人数
                     * // 如果上一步所剩下的雇佣兵人数小于零，即表示战斗数k对应的雇佣兵不够打
                     * // 则 上一个battle[k]的绝对值就是剩下的雇佣兵人数
                     * // 所以，这一步就是把battle[k]的绝对值赋值给totalOrc供战斗数-1的雇佣兵打
                     * // 如果上一步所剩下的雇佣兵人数大于等于零，即表示战斗数k对应的雇佣兵够打，则结束while
                     * totalOrc = -battle[k];
                     * if (battle[k] < 0) {
                     * battle[k] = 0;
                     * }
                     * k--;
                     * }
                     */
                    while (totalOrc > 0) {
                        if (totalOrc - battle[k] >= 0) {
                            totalOrc -= battle[k];
                            battle[k] = 0;
                        } else {
                            battle[k] -= totalOrc;
                            totalOrc = 0;
                        }
                        k--;
                    }

                    // battle次数+1
                    // 战斗两次一定全部消耗完
                    battle[2] = battle[1];
                    battle[1] = battle[0];
                    battle[0] = 0;

                    dfs(nowMoney, passTime + 1);
                    battle[2] = temp2;
                    battle[1] = temp1;
                    battle[0] = temp0;
                }
                // 人数不够，同时执行结束了，不用处理
            }
        }
    }
}
