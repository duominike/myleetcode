package linklist;/*
 * @Author: Marte
 * @Date:   2020-04-15 13:35:11
 * @Last Modified by:   Marte
 * @Last Modified time: 2020-04-15 14:18:41
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solutions {

    /**
     * Q2. 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * [2 ——>4 -> 3, 5 ->6 ->4 = 807]
     * 先求和，构建链表，最后处理进位问题
     *
     * @param node1 [description]
     * @param node2 [description]
     * @return [description]
     */
    public static ListNode addTwoNumber(ListNode node1, ListNode node2) {
        if (node1 == null) {
            node1 = new ListNode(0);
        }

        if (node2 == null) {
            node2 = new ListNode(0);
        }

        ListNode rs = new ListNode(node1.val + node2.val);
        ListNode temp = rs;
        node1 = node1.next;
        node2 = node2.next;
        while (node1 != null || node2 != null) {
            int a = 0;
            int b = 0;
            if (node1 != null) {
                a = node1.val;
            }

            if (node2 != null) {
                b = node2.val;
            }

            temp.next = new ListNode(a + b);
            temp = temp.next;
            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }

        }

        temp = rs;
        while (temp != null) {
            if (temp.val >= 10) {
                temp.val = temp.val - 10;
                if (temp.next == null) {
                    temp.next = new ListNode(0);
                }
                temp.next.val = temp.next.val + 1;
            }
            temp = temp.next;
        }

        return rs;
    }

    /**
     * Q19. 删除链表的倒数第N个节点
     * 删除链表的倒数第n个元素
     * 先定位到倒数第length - (N+1)个位置，移动到队尾，找到要删除的前一个元素。
     *
     * @param head [description]
     * @param n    [description]
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode summary = new ListNode(0);
        summary.next = head;
        ListNode first = summary;
        ListNode second = summary;
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        if (second.next == head) {
            return head.next;
        } else {
            second.next = second.next.next;
        }
        return head;
    }

    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * 先将链表变成循环链表，然后找到要断开的目标位置，断开，返回新的头结点
     * 链表向右移动k个位置
     *
     * @param head [description]
     * @param n    [description]
     * @return [description]
     */
    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode oldTail = head;
        int length = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            length++;
        }

        oldTail.next = head;
        ListNode newTail = head;
        for (int i = 0; i < length - n % length - 1; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }

    /**
     * 206. 反转链表
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *
     * @param head
     * @return
     */
    public static ListNode reverseLinkList(ListNode head) {
        ListNode temp = head;
        ListNode pre = null;
        while (temp != null) {
            ListNode t = temp.next;
            temp.next = pre;
            pre = temp;
            temp = t;
        }
        return pre;
    }

    /**
     * Q24/Q25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseLinkOfK(ListNode head, int k) {
        ListNode temp = head;
        ListNode summary = null;
        ListNode curr;
        ListNode prev = null;
        ArrayDeque<ListNode> groupNodes = new ArrayDeque<>();
        int count = 0;
        while (temp != null) {
            groupNodes.add(temp);
            count++;
            if (count == k) {
                curr = groupNodes.pollLast();
                temp = curr.next;
                if (prev == null) {
                    summary = curr;
                } else {
                    prev.next = curr;
                }
                prev = curr;
                while (!groupNodes.isEmpty()) {
                    curr = groupNodes.pollLast();
                    prev.next = curr;
                    prev = curr;
                }
                prev.next = null;
                count = 0;
            } else {
                temp = temp.next;
            }
        }
        if (summary == null) {
            summary = head;
        }
        if (!groupNodes.isEmpty() && groupNodes.size() < k) {
            while (!groupNodes.isEmpty()) {
                curr = groupNodes.pollFirst();
                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;
            }
        }
        return summary;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head
     * @return
     */

    public static ListNode reverseOfk(ListNode head, int groupCount) {
        int count = 0;
        ListNode temp = head;
        while (count < groupCount && temp != null) {
            temp = temp.next;
            count++;
        }
        if (count == groupCount) {
            ListNode newTail = head;
            ListNode curr = head;
            ListNode next = null;
            while (count > 0) {
                ListNode tmp = curr;
                curr = curr.next;
                tmp.next = next;
                next = tmp;
                count--;
            }
            newTail.next = reverseOfk(curr, groupCount);
            return next;
        } else {
            return head;
        }

    }

    public static ListNode swapPairs(ListNode head) {
        return reverseOfk(head, 2);
    }

    /**
     * 21： 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode newHead = null;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode res = null;
        ListNode resNext = null;
        while (curr1 != null || curr2 != null) {
            if (curr1 == null) {
                resNext = curr2;
                curr2 = curr2.next;
            } else if (curr2 == null) {
                resNext = curr1;
                curr1 = curr1.next;
            } else {
                if (curr1.val <= curr2.val) {
                    resNext = curr1;
                    curr1 = curr1.next;
                } else {
                    resNext = curr2;
                    curr2 = curr2.next;
                }
            }

            if (res == null) {
                res = resNext;
            } else {
                res.next = resNext;
                res = res.next;
            }
            if (newHead == null) {
                newHead = res;
            }
        }
        return newHead;
    }

    /**
     * 83
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode temp = head;
        ListNode res = null;
        while (temp != null) {
            boolean delTemp = false;
            while (temp.next != null && temp.next.val == temp.val) {
                ListNode n1 = temp.next;
                temp.next = n1.next;
                n1.next = null;
                delTemp = true;
            }
            if (delTemp) {
                if (res == null) {
                    res = temp.next;
                }
                if (prev == null) {
                    prev = temp;
                    temp = temp.next;
                    prev.next = null;
                    prev = null;
                } else {
                    prev.next = temp.next;
                    temp.next = null;
                    temp = prev.next;
                }
            } else {
                prev = temp;
                temp = temp.next;
                if (res == null) {
                    res = prev;
                }
            }
        }
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Q106: 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * <p>
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * <p>
     * 示例:
     * <p>
     * 给定的有序链表： [-10, -3, 0, 5, 9],
     * <p>
     * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
     * <p>
     * 0
     * / \
     * -3   9
     * /   /
     * -10  5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            TreeNode node = new TreeNode(head.val);
            node.left = null;
            node.right = null;
            return node;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.next == null) {
                break;
            }
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode node = new TreeNode(slow.val);
        prev.next = null;
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);
        return node;
    }

    /**
     * Q138: 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
     * <p>
     * 要求返回这个链表的 深拷贝。
     *
     * @param
     * @return
     */
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> maps = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            maps.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            maps.get(cur).next = maps.get(cur.next);
            maps.get(cur).random = maps.get(cur.random);
            cur = cur.next;
        }
        return maps.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node temp = new Node(cur.val);
            temp.next = cur.next;
            cur.next = temp;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = (cur.random != null) ? cur.random.next : null;
            cur = cur.next.next;
        }

        Node ptrOldList = head;
        Node ptrNewList = head.next;
        Node headOld = head.next;
        while (ptrOldList != null) {
            ptrOldList.next = ptrOldList.next.next;
            ptrNewList.next = (ptrNewList.next != null) ? ptrNewList.next.next : null;
            ptrOldList = ptrOldList.next;
            ptrNewList = ptrNewList.next;
        }
        return headOld;
    }
    /*------------------------------------------------*/

    /**
     * Q23: 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     *
     * 示例:
     *
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param lists
     * @return
     */
    public static ListNode mergeKLists23(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }
        ListNode result = lists[0];
        for(int i = 1; i<lists.length; i++){
            result = mergeTwoLists23(result, lists[i]);
        }
        return result;
    }

    public static ListNode mergeTwoLists23(ListNode node1, ListNode node2){
        ListNode head = node1;
        ListNode curr1 = node1;
        ListNode prev1 = null;
        ListNode curr2 = node2;
        while(curr2 != null){
            while(curr1 != null && curr1.val < curr2.val){
                prev1 = curr1;
                curr1 = curr1.next;
            }
            if(prev1 != null){
                prev1.next = curr2;
                ListNode temp = curr2.next;
                curr2.next = curr1;
                prev1 = curr2;
                curr2 = temp;
            }else{
                ListNode temp = curr2.next;
                curr2.next = curr1;
                prev1 = curr2;
                head = prev1;
                curr2 = temp;
            }
        }
        return head;
    }
}