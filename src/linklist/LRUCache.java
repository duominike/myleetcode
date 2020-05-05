package linklist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class LRUNode {
        int val;
        LRUNode pre;
        LRUNode next;

        public LRUNode(int val) {
            this.val = val;
        }
    }

    private LRUNode tail;
    private LRUNode head;

    private Map<Integer, LRUNode> table = new HashMap<>();
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        LRUNode node = table.get(key);
        if (node != null) {
            // 把这个节点删除并插入到头结点
            removeAndInsert(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (head == null) {
            head = new LRUNode(value);
            tail = head;
            table.put(key, head);
            return;
        }
        if (table.containsKey(key)) {
            LRUNode node = table.get(key);
            node.val = value;
            removeAndInsert(node);
        } else {
            LRUNode tmp = new LRUNode(value);
            // 如果会溢出
            if (table.size() >= capacity) {
                // 先把它从哈希表中删除
                table.remove(tail);
                // 删除尾部节点
                tail = tail.pre;
                tail.next = null;
            }
            table.put(key, tmp);
            // 插入
            tmp.next = head;
            head.pre = tmp;
            head = tmp;
        }
    }

    private void removeAndInsert(LRUNode node) {
        if (node == head) {
            return;
        } else if (node == tail) {
            tail = node.pre;
            tail.next = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }


        head.pre = node;
        node.next = head;
        node.pre = null;
        head = node;
    }
}
