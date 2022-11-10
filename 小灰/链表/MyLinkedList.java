package 小灰.链表;

/**
 * 链表
 */
public class MyLinkedList {

    private Node header;
    private Node tailer;

    private int size;

    /**
     * 插入数据
     * 
     * @param data  数据
     * @param index 插入位置
     */
    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node newNode = new Node(data);

        if (size == 0) {
            // 头节点
            newNode.next = null;
            header = newNode;
            tailer = newNode;
        } else if (index == 0) {
            // 插入头部
            newNode.next = header;
            header.next = null;

            header = newNode;
        } else if (index == size) {
            // 插入尾部
            tailer.next = newNode;
            newNode.next = null;

            tailer = newNode;
        } else {
            // 插入中间
            Node preNode = get(index - 1);
            newNode.next = preNode.next;
            preNode.next = newNode;
        }

        size++;
    }

    /**
     * 删除元素
     * 
     * @param index 删除位置
     * @return 删除的节点
     */
    public Node remove(int index) throws Exception {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node removedNode;

        if (index == 0) {
            // 删除头节点
            removedNode = header;
            header = header.next;

            if (size == 1) {
                // 只有一个元素
                header = null;
                tailer = null;
            }
        } else if (index == size - 1) {
            // 删除尾节点
            Node preNode = get(index - 1);

            removedNode = preNode.next;

            preNode.next = null;

            tailer = preNode;
        } else {
            // 删除中间节点
            Node preNode = get(index - 1);
            preNode.next = preNode.next.next;

            removedNode = preNode.next;
        }

        size--;

        return removedNode;
    }

    /**
     * 查找链表元素
     * 
     * @param index 位置
     */
    private Node get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node temp = header;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    public void output() {
        Node temp = header;
        System.out.print("链表：");
        while (temp != null) {
            System.out.print(temp.data + " ");

            temp = temp.next;
        }
        System.out.println();
    }

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        try {
            myLinkedList.insert(3, 0);
            myLinkedList.insert(7, 1);
            myLinkedList.insert(9, 2);
            myLinkedList.insert(5, 3);
            myLinkedList.insert(6, 1);
            myLinkedList.remove(0);
            myLinkedList.output();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
