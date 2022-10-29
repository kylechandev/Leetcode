
/**
 * 11. 盛最多水的容器
 * 
 * 中等
 * 
 * 双指针（头尾）
 * 
 * https://leetcode.cn/problems/container-with-most-water/
 */
public class 盛最多水的容器 {

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 
     * 返回容器可以储存的最大水量。
     * 
     * 说明：你不能倾斜容器。
     */
    public static int maxArea(int[] height) {
        // height数组的值是每条垂线的高度

        // 可容纳水的高度由两板中的 短板 决定，因此可得如下 面积公式 ：
        // S(l,r)=min(h[l],h[r])×(r−l)

        // n条垂线
        int n = height.length;

        int l = 0;
        int r = n - 1;

        int max = 0;

        while (l < r) {
            int w = r - l;
            int h = Math.min(height[l], height[r]);
            int sum = w * h;
            max = Math.max(max, sum);

            // 小的前进
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int maxArea = maxArea(height);
        System.out.println("max area: " + maxArea);
    }
}
