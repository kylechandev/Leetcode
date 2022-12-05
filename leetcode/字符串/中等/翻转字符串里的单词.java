package leetcode.字符串.中等;

/**
 * 151. 翻转字符串里的单词
 * 
 * 中等
 * 
 * 
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * 
 * 
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class 翻转字符串里的单词 {

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        // 1、去除多余空格
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') {
            start++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }

        // 2、反转整个字符串
        reverse(sb, 0, sb.length() - 1);

        // 3、反转单词
        sb.append(' ');
        int pre = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                // 反转前i个
                reverse(sb, pre, i - 1);
                pre = i + 1;
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static void reverse(StringBuilder sb, int from, int to) {
        int left = from;
        int right = to;
        while (left < right) {
            char leftChar = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, leftChar);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords(s));
    }
}
