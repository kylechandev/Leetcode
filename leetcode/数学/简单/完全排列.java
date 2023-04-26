package leetcode.数学.简单;

import java.util.ArrayList;
import java.util.List;

/**
 * 例如给出AB
 * 
 * 返回 AA AB BA BB 所有可能得组合
 */
public class 完全排列 {

    final List<String> ans = new ArrayList<>();
    private final List<String> path = new ArrayList<>();

    public List<String> solution(char[] c, int i) {
        dfs(c, i);

        return ans;
    }

    public void dfs(char[] c, int i) {
        // 边界条件
        if (i == c.length) {
            // 找到结果
            ans.add(String.join("", path));
            return;
        }

        for (char v : c) {
            path.add(String.valueOf(v));
            dfs(c, i + 1);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        String s = "ABCD";
        System.out.println(new 完全排列().solution(s.toCharArray(), 0));
        System.out.println(new 完全排列().solution(s.toCharArray(), 0).size());
    }
}
