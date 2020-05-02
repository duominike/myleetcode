package linklist;/*
 * @Author: Marte
 * @Date:   2020-04-15 13:35:11
 * @Last Modified by:   Marte
 * @Last Modified time: 2020-04-15 14:18:41
 */

import java.util.ArrayDeque;
import java.util.ArrayList;

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
     * 合并两个有序链表
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
}