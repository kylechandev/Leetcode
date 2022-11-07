package 小灰.栈和队列;

/**
 * 栈
 * 
 * 入栈
 * 出栈
 * 
 * 判断栈满
 * 判断栈空
 */
public class MyStack {

    private int[] array;
    // 栈顶
    private int top;
    // 栈底
    private int bottom = -1;

    MyStack(int capacity) {
        this.array = new int[capacity];
    }

    /**
     * 入栈
     * 
     * @param data 入栈元素
     */
    public void push(int data) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[++bottom] = data;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int data = array[bottom--];
        return data;
    }

    /**
     * 判断栈空
     */
    public boolean isEmpty() {
        return top == bottom;
    }

    /**
     * 判断是否栈满
     */
    public boolean isFull() {
        return bottom == array.length - 1;
    }

    public void output() {
        for (int i = top; i <= bottom; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(10);
        myStack.push(12);
        myStack.push(1);
        myStack.push(2);
        myStack.pop();
        myStack.pop();
        myStack.output();
    }
}
