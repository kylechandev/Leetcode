/*
 * @Author: kaic
 * @Date: 2022-11-07 22:52:09
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-08 20:54:53
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.树.二叉树;

/**
 * 二叉树
 * 
 * 实现方式1：链表
 * data, left-node, right-node
 * 
 * 实现方式2：数组 - 不适合稀疏二叉树，浪费空间
 * 按层级顺序将节点数据存储顺序存储到数组，空缺的孩子节点在数组中对应位置也空出
 * 
 * 
 * 【完全二叉树】
 * 若设二叉树的深度为h，除第 h 层外，其它各层 (1~h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在`最左边`，这就是完全二叉树
 * 
 * 【叶子节点】
 * 没有子节点的节点
 * 
 * 
 * 【父节点 / 子节点 位置查询】
 * 已知 parentIndex
 * leftChildIndex = parentIndex * 2 + 1
 * rightChildIndex = parentIndex * 2 + 2
 * 
 * 已知 leftChildIndex 或者 rightChildIndex 其中任意一个（以下简称为childIndex）
 * parentIndex = (childIndex - 1) / 2
 * 【这里是`/`运算，取的商，不是余数，所以不管是左还是右子节点都可以通过一个公式计算得到父节点】
 * 
 * 
 * 
 * 【二叉树的应用】
 * 
 * 1、查找
 * 
 * 【二叉查找树】 - 依靠比较大小来逐步查找（类似于二分查找） - 时间复杂度 O(logn)
 * - 左子树的值都比根节点的值小
 * - 右子树的值都比根节点的值大
 * - 左右子树也都是二叉查找树
 * -【以上特性 保证了二叉树的`有序性`】
 * 
 * 2、维持相对顺序
 * 二叉查找树 另一个名字 【二叉排序树】 - 看上面列出的二叉查找树的特性
 * 
 * 
 * 【存在问题】
 * 
 * 例如数据：10[根节点] 9 8 7 6 5 4 3 2 1
 * 会导致所有节点全部在左侧，而且时间复杂度也变成了O(n)
 * 
 * 【解决问题】
 * 
 * [二叉树的自平衡] 方式：红黑树、AVL树、树堆等等
 * 
 * 
 * 【还有 - 二叉堆】
 * 
 * 像二叉排序树一样，[但是只要求父节点比它的左右孩子大]
 */
public class BinaryTree {
    public static void main(String[] args) {

    }
}
