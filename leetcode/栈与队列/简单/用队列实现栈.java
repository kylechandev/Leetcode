package leetcode.栈与队列.简单;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * 
 * 简单
 * 
 * 
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 
 * 
 * https://leetcode.cn/problems/implement-stack-using-queues/
 */
public class 用队列实现栈 {

    public static void main(String[] args) {

    }
}

class MyStack {

    // 和栈中元素保持一致
    private Queue<Integer> queue1 = new ArrayDeque<>();
    // 复制队列
    private Queue<Integer> queue2 = new ArrayDeque<>();

    public MyStack() {
    }

    public void push(int x) {
        queue2.offer(x);

        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }

        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
        
    public int pop() {
        return queue1.poll();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
