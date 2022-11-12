package 小灰.散列表;

/**
 * 散列表（可以看作数组+链表的结合）
 * 
 * 原理：数组实现
 * 
 * key 与 数组下标 的转换 => 转换方法就是：哈希函数
 * 
 * 数组下标计算：
 * index = hashcode(key) % array.lengh
 * 
 * 【可能存在哈希冲突：同一个hashcode计算出来的index值相同】
 * 
 * 解决哈希冲突：1、开放寻址法（ThreadLocal采用）
 * 比如说：index=3已经有数据了，那么就看看index=4...5...6，找到一个空位置
 * 
 * 解决哈希冲突：2、链表法（HashMap采用）
 * 数组中的每一个元素都是一个链表的头节点，冲突的元素依次添加到`冲突位置`所在的链表尾部
 * 
 * 
 * 【扩容机制】
 * 
 * Capacity：HashMap的当前长度
 * LoadFactor：HashMap的负载因子，默认值为0.75f （避免某个位置下面的链表过长）
 * 
 * 扩容条件：HashMap.size（已经存储的元素数量） >= Capacity * LoadFactor
 */
public class Hash {
    public static void main(String[] args) {

    }
}
