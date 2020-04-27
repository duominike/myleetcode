package stack;

import java.util.ArrayDeque;
import java.util.Deque;

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
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        stack.add(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.getLast() != -1 && heights[i] <= heights[stack.getLast()]) {
                int top = stack.pollLast();
                ans = Math.max(heights[top] * (i - stack.getLast() - 1), ans);
            }
            stack.add(i);
        }
        while (stack.getLast() != -1) {
            int top = stack.pollLast();
            ans = Math.max(heights[top] * (heights.length - stack.getLast() - 1), ans);
        }
        return ans;
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
                    while (!stackOpera.isEmpty()){
                        if(stackOpera.getLast() == '('){
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
}
