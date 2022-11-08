package 小灰.树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的遍历
 * 
 * 1、【深度优先遍历】
 * -【前序遍历】 根->左->右
 * -【中序遍历】 左->根->右
 * -【后序遍历】 左->右->根
 * 
 * 2、【广度优先遍历】
 * -【层序遍历】
 * 
 */
public class BinaryTreeTraversal {

    /**
     * 通过数组创建二叉树
     * 
     * @param array       数组
     * @param parentIndex 父节点位置
     */
    public TreeNode createBinaryTreeByArray(Integer[] array, int parentIndex) {
        TreeNode treeNode = null;

        if (parentIndex < array.length) {
            // 节点数据
            Integer data = array[parentIndex];

            if (data != null) {
                // 节点存在
                // 创建父节点
                treeNode = new TreeNode(data);

                // 左孩子
                treeNode.left = createBinaryTreeByArray(array, parentIndex * 2 + 1);
                // 右孩子
                treeNode.right = createBinaryTreeByArray(array, parentIndex * 2 + 2);
            }
        }

        // 返回根节点
        return treeNode;
    }

    /**
     * 通过List创建二叉树 - List中元素的顺序需要是二叉树的前序遍历顺序
     * 
     */
    public TreeNode createBinaryTreeByList(LinkedList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        TreeNode treeNode = null;

        Integer data = list.removeFirst();

        if (data != null) {
            // 有节点

            // 创建父节点
            treeNode = new TreeNode(data);

            // 创建左孩子
            treeNode.left = createBinaryTreeByList(list); // 递归 栈 的特性，把所有左边的节点都遍历完成后开始回溯结果
            // 创建右孩子
            treeNode.right = createBinaryTreeByList(list); // 并紧接着开始递归右节点。这就是参数list元素必须是前序遍历顺序的原因
        }

        return treeNode;
    }

    /**
     * 前序遍历
     */
    public static void preOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.print(treeNode.data + " ");
        preOrderTraversal(treeNode.left);
        preOrderTraversal(treeNode.right);
    }

    /**
     * 中序遍历
     */
    public static void inOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        inOrderTraversal(treeNode.left);
        System.out.print(treeNode.data + " ");
        inOrderTraversal(treeNode.right);
    }

    /**
     * 后序遍历
     */
    public static void postOrderTraversal(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        postOrderTraversal(treeNode.left);
        postOrderTraversal(treeNode.right);
        System.out.print(treeNode.data + " ");
    }

    /**
     * 前序遍历 - 通过栈实现
     */
    public static void preOrderTraversalByStack1(TreeNode rootNode) {
        Stack<TreeNode> stack = new Stack<>();

        // 入栈根节点
        stack.push(rootNode);

        while (!stack.isEmpty()) {
            // 出栈（遍历）根节点
            TreeNode treeNode = stack.pop();
            System.out.print(treeNode.data + " ");

            // 先入栈 右 节点（因为栈是先进后出）
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            // 再入栈 左 节点（因为栈是先进后出）
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }

    /**
     * 中序遍历 - 通过栈实现
     */
    public static void inOrderTraversalByStack(TreeNode rootNode) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode treeNode = rootNode;

        // 左 根 右

        // 1、左侧一直入栈到最底部左侧的那个

        while (treeNode != null || !stack.isEmpty()) {
            // 节点不为空时
            while (treeNode != null) {
                // 前序的话，在这里输出
                // System.out.print(treeNode.data + " ");

                // 入栈
                stack.push(treeNode);
                // 继续遍历左节点，直到左边为空
                treeNode = treeNode.left;
            }

            // 已经遍历到左侧最后一个子节点了

            // 当栈不为空时
            if (!stack.isEmpty()) {
                // 开始出栈左侧子节点
                treeNode = stack.pop();
                System.out.print(treeNode.data + " ");

                // 再次指向右节点，判断最后一层的左子节点是否存在右节点
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 后序遍历 - 通过栈实现
     */
    public static void postOrderTraversalByStack(TreeNode rootNode) {
        // 栈1
        Stack<TreeNode> stack = new Stack<>();
        // 栈2
        Stack<TreeNode> res = new Stack<>();

        // 【根 右 左】的顺序入栈res
        // res出栈，变为后序顺序【左 右 根】

        TreeNode treeNode = rootNode;

        stack.push(treeNode);

        // 当栈不为空的时循环
        while (!stack.empty()) {
            // pop出栈顶的结点，同时把该结点的孩子结点入栈
            TreeNode pop = stack.pop();
            // pop元素压入res栈中
            res.push(pop);

            // 压入左结点
            if (pop.left != null)
                stack.push(pop.left);
            // 先压入右结点
            if (pop.right != null)
                stack.push(pop.right);
        }
        // 后序遍历
        while (!res.empty()) {
            // 访问操作
            TreeNode pop = res.pop();
            System.out.print(pop.data + " ");
        }
    }

    /**
     * 后序遍历 - 通过栈实现
     */
    public static void postOrderTraversalByStack2(TreeNode rootNode) {
        Stack<TreeNode> stack = new Stack<>();

        TreeNode treeNode = rootNode;

        // 左 根 右

        // 1、左侧一直入栈到最底部左侧的那个

        while (treeNode != null || !stack.isEmpty()) {
            // 节点不为空时
            while (treeNode != null) {
                // 入栈
                stack.push(treeNode);
                // 继续遍历左节点，直到左边为空
                treeNode = treeNode.left;
            }

            // 已经遍历到左侧最后一个子节点了

            // 当栈不为空时
            if (!stack.isEmpty()) {
                // 开始出栈左侧子节点
                treeNode = stack.pop();
                System.out.print(treeNode.data + " ");

                // 再次指向右节点，判断最后一层的左子节点是否存在右节点
                treeNode = treeNode.right;
            }
        }
    }

    /**
     * 层序遍历 - 通过队列实现
     */
    public static void levelOrderTraversal(TreeNode rootNode) {
        // add 增加一个元索 如果队列已满，则抛出一个IIIegaISlabEepeplian异常
        // remove 移除并返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
        // element 返回队列头部的元素 如果队列为空，则抛出一个NoSuchElementException异常
        // offer 添加一个元素并返回true 如果队列已满，则返回false
        // poll 移除并返问队列头部的元素 如果队列为空，则返回null
        // peek 返回队列头部的元素 如果队列为空，则返回null
        // put 添加一个元素 如果队列满，则阻塞
        // take 移除并返回队列头部的元素 如果队列为空，则阻塞
        // drainTo(list) 一次性取出队列所有元素

        // 知识点： remove、element、offer 、poll、peek 其实是属于Queue接口

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        // 入队列 根节点
        queue.offer(rootNode);

        while (!queue.isEmpty()) {
            // 出队列
            TreeNode header = queue.poll();
            System.out.print(header.data + " ");

            // 依此入队列 左节点
            TreeNode left = header.left;
            if (left != null) {
                queue.offer(left);
            }

            // 依此入队列 右节点
            TreeNode right = header.right;
            if (right != null) {
                queue.offer(right);
            }
        }
    }

    private static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private Integer data;

        TreeNode(Integer data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();

        // 创建二叉树的源数组
        Integer[] array = new Integer[] { 1, 2, 3, 4, 5, null, 6 };

        // 创建二叉树
        TreeNode treeNode = binaryTreeTraversal.createBinaryTreeByArray(array, 0);

        System.out.println("前序遍历：");
        preOrderTraversal(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("前序遍历（Stack）：");
        preOrderTraversalByStack1(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("中序遍历：");
        inOrderTraversal(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("中序遍历（Stack）：");
        inOrderTraversalByStack(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("后序遍历：");
        postOrderTraversal(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("后序遍历（Stack）：");
        postOrderTraversalByStack(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("后序遍历（Stack）2：");
        postOrderTraversalByStack2(treeNode);
        System.out.println();
        System.out.println();

        System.out.println("层序遍历：");
        levelOrderTraversal(treeNode);
        System.out.println();
        System.out.println();
    }
}
