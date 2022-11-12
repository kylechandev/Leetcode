/*
 * @Author: kaic
 * @Date: 2022-11-12 15:18:13
 * @LastEditors: kylechandev kylechan47@gmail.com
 * @LastEditTime: 2022-11-12 17:07:12
 * Copyright (c) 2022 by kylechandev kylechan47@gmail.com, All Rights Reserved. 
 */
package 小灰.算法的实际应用;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRUCache - 最近最少使用算法
 * 
 * 采用数据结构：哈希链表`LinkedHashMap`（但是LinkedHashMap并不是LRU算法）
 */
public class LRUCache {

    // 链表长度
    private int limit;

    // 头节点
    private Node head;
    // 尾节点
    private Node tail;

    // 承载所有的节点
    private HashMap<String, Node> hashMap;

    LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<String, Node>();
    }

    public synchronized String get(String key) {
        Node node = hashMap.get(key);

        if (node != null) {
            refreshNode(node);
            return node.value;
        } else {
            return null;
        }
    }

    public synchronized void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            // 插入新节点
            if (hashMap.size() >= limit) {
                // 超过容量，需要删除最不经常访问的那个（即头节点）
                String deletedKey = removeNode(head);
                hashMap.remove(deletedKey);
            }
            // 插入尾部
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            // 更新旧节点
            node.value = value;
            refreshNode(node);
        }
    }

    public synchronized String remove(String key) {
        Node node = hashMap.get(key);

        removeNode(node);
        return hashMap.remove(key).value;
    }

    /**
     * 刷新节点
     */
    private void refreshNode(Node node) {
        if (node == null || node == tail) {
            return;
        }

        removeNode(node);
        addNode(node);
    }

    /**
     * 添加节点
     */
    private void addNode(Node node) {
        if (tail != null) {
            // 插入尾部
            tail.next = node;
            node.pre = tail;
            node.next = null;
        }

        // 更新尾部
        tail = node;

        // 更新头部
        if (head == null) {
            head = node;
        }
    }

    /**
     * 删除节点
     */
    private String removeNode(Node node) {
        if (node == null) {
            return null;
        }

        if (node == head && node == tail) {
            // 删除了唯一节点
            head = null;
            tail = null;
        } else if (node == head) {
            // 删除了头节点
            head = head.next;
            head.pre = null;
        } else if (node == tail) {
            // 删除了尾节点
            tail = tail.pre;
            tail.next = null;
        } else {
            // 删除了中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        return node.key;
    }

    private static class Node {
        Node pre;
        Node next;

        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;

            pre = null;
            next = null;
        }
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(3);
        lru.put("1", "1");
        lru.put("2", "2");
        lru.put("3", "3");

        System.out.println(lru.get("1"));

        // 【注意】
        // LinkedHashMpa内部不是LRU算法！
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("星期一", 40);
        map.put("星期二", 43);
        map.put("星期三", 35);
        map.put("星期四", 55);
        map.put("星期五", 45);
        map.put("星期六", 35);
        map.put("星期日", 30);
        // 使用某个元素后，并不会改变他的顺序
        map.get("星期三");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
