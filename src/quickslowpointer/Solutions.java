package quickslowpointer;

import linklist.ListNode;

public class Solutions {
    /**
     * Q14: 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String str1 = strs[0];
        StringBuilder pre = new StringBuilder();
        int i = 0;
        while (true) {
            if (pre.length() == str1.length()) {
                break;
            }
            char curr = str1.charAt(i);
            for (int k = 1; k < strs.length; k++) {
                if (pre.length() == strs[k].length() || curr != strs[k].charAt(i)) {
                    break;
                }
            }
            pre.append(curr);
            i++;
        }
        return pre.toString();
    }

    /**
     * Q141:给定一个链表，判断链表中是否有环。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    private static int bitSquareSum(int n) {
        int sum = 0;
        while (n != 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }

    /**
     * Q202: 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     *
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/happy-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = bitSquareSum(n);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        } while (slow != fast);
        return slow == 1;
    }

    /**
     *
     * 876. 链表的中间结点
     * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
     *  如果有两个中间结点，则返回第二个中间结点。
     */


    public ListNode middleNode(ListNode head) {
        if(head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast == null){
            return slow;
        }else{
            return slow.next;
        }

    }
}
