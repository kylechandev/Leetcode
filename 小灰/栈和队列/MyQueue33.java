package 小灰.栈和队列;

/**
 * 队列
 * 
 * 数组实现（循环队列）
 * 
 * 入队列
 * 出队列
 * 
 * 判断队列是否已经满
 * 判断队列是否为空
 */
public class MyQueue33 {

    private int[] array;
    private int front;
    private int rear;

    MyQueue33(int capacity) {
        this.array = new int[capacity];
    }

    public void enqueue(int data) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[rear] = data;
        rear = nextPoint(rear);
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int data = array[front];
        front = nextPoint(front);
        return data;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isFull() {
        return false;
    }

    private int nextPoint(int point) {
        return (point + 1) % array.length;
    }

    public void output() {
        for (int i = front; i != rear; i = nextPoint(i)) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue(6);
        myQueue.enqueue(3);
        myQueue.enqueue(5);
        myQueue.enqueue(6);
        myQueue.enqueue(8);
        myQueue.enqueue(1);
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.dequeue();
        myQueue.enqueue(2);
        myQueue.enqueue(4);
        myQueue.output();
    }
}
