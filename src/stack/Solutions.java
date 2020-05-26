package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

class Solutions {
    /**
     * 出入堆栈
     * 是否为有效括号
     *
     * @param s
     * @return
     */
    public static boolean isValidStr(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        char indexChar, head;
        for (int i = 0; i < s.length(); i++) {
            indexChar = s.charAt(i);
            if (indexChar == '(' || indexChar == '[' || indexChar == '{') {
                stack.add(indexChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                head = stack.getLast();
                if ((head == '(' && indexChar == ')')
                        || (head == '{' && indexChar == '}')
                        || (head == '[' && indexChar == ']')) {
                    stack.pollLast();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Q32. 最长有效括号
     * 堆栈入索引：栈为空，将要出堆栈的索引先入队，遍历过程中求结果
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int ans = 0;
        Deque<Integer> indexs = new ArrayDeque<>();
        indexs.add(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                indexs.add(i);
            } else {
                indexs.pollLast();
                if (indexs.isEmpty()) {
                    indexs.add(i);
                } else {
                    ans = Math.max(ans, i - indexs.getLast());
                }
            }
        }
        return ans;

    }

    /**
     * Q84:给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * [2,1,5,6,2,3]
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        int n = heights.length;
        int max = 0;
        for(int i = 0; i<=n; i++){
            while(!stack.isEmpty() && (i == n || heights[i] < heights[stack.peek()])){
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i-1-stack.peek();
                max = Math.max(max, height * width);
            }
            stack.add(i);
        }
        return max;
    }

    /**
     * Q224: 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     * <p>
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     * <p>
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     * 说明：
     * <p>
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static int calculate(String s) {
        Deque<Character> stackOpera = new ArrayDeque<>();
        Deque<Integer> stackNum = new ArrayDeque<>();
        char[] characters = s.toCharArray();
        boolean hasNum = false;
        int num = 0;
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == ' ') {
                continue;
            }
            if (Character.isDigit(characters[i])) {
                num = num * 10 + (characters[i] - '0');
                hasNum = true;
            } else {
                if (hasNum) {
                    stackNum.add(num);
                    hasNum = false;
                    num = 0;
                }

                if (isOperaAnd(characters[i])) {
                    while (!stackOpera.isEmpty()) {
                        if (stackOpera.getLast() == '(') {
                            break;
                        }
                        int num2 = stackNum.pollLast();
                        int num1 = stackNum.pollLast();
                        char oper = stackOpera.pollLast();
                        stackNum.add(calcTwoNumByOper(num1, num2, oper));
                    }
                    stackOpera.add(characters[i]);

                } else if (characters[i] == '(') {
                    stackOpera.add(characters[i]);
                } else if (characters[i] == ')') {
                    while (!stackOpera.isEmpty()) {
                        char oper = stackOpera.pollLast();
                        if (oper == '(') {
                            break;
                        }
                        int num2 = stackNum.pollLast();
                        int num1 = stackNum.pollLast();
                        stackNum.add(calcTwoNumByOper(num1, num2, oper));
                    }
                }
            }
        }

        if (hasNum) {
            stackNum.add(num);
        }

        while (!stackOpera.isEmpty()) {
            char oper = stackOpera.pollLast();
            int num2 = stackNum.pollLast();
            int num1 = stackNum.pollLast();
            stackNum.add(calcTwoNumByOper(num1, num2, oper));
        }

        return stackNum.pollLast();

    }

    public static boolean isOperaAnd(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int calcTwoNumByOper(int num1, int num2, char oper) {
        if (oper == '+') {
            return num1 + num2;
        } else if (oper == '-') {
            return num1 - num2;
        } else if (oper == '*') {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    /**
     * Q316: 你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。
     * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: "bcabc"
     * 输出: "abc"
     * 示例 2:
     * <p>
     * 输入: "cbacdcbc"
     * 输出: "acdb"
     * bbcaac
     * bac
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.contains(c)) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && s.indexOf(stack.peek(), i) != -1) {
                stack.pop();
            }
            stack.push(c);
        }
        String rs = "";
        for (int i = 0; i < stack.size(); i++) {
            rs += stack.get(i);
        }
        return rs;
    }
}
