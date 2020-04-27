package backtrack;

import linklist.ListNode;

import java.util.*;

public class Solutions {
    /**
     * Q51:
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * <p>
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <p>
     * 示例:
     * <p>
     * 输入: 4
     * 输出: [
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] columns = new int[n];
        for (int i = 0; i < n; i++) {
            columns[i] = -1;
        }
        backTrack51(0, n, columns, res);
        return res;
    }

    public static void backTrack51(int row, int n, int[] columns, List<List<String>> res) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (columns[i] == j) {
                        stringBuilder.append("Q");
                    } else {
                        stringBuilder.append(".");
                    }
                }
                list.add(stringBuilder.toString());
            }
            res.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            columns[row] = i;
            if (valid(row, i, columns)) {
                backTrack51(row + 1, n, columns, res);
            }
            columns[row] = -1;
        }
    }

    public static boolean valid(int row, int col, int[] columns) {
        for (int i = 0; i < row; i++) {
            if (columns[i] == col || row - i == Math.abs(columns[i] - col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Q39:
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> oneResult = new ArrayDeque<>();
        backTrace39(0, candidates, target, res, oneResult);
        return res;
    }

    public static void backTrace39(int start, int[] candidates, int target, List<List<Integer>> res,
                                   Deque<Integer> solution) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<Integer>(solution));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            solution.add(candidates[i]);
            backTrace39(i, candidates, target - candidates[i], res, solution);
            solution.removeLast();
        }
    }


    /**
     * q91:一条包含字母 A-Z 的消息通过以下方式进行了编码：
     * <p>
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2:
     * <p>
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-ways
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        char[] inputs = s.toCharArray();
        return backTrace91(inputs, inputs.length - 1);
    }

    public static int backTrace91(char[] inputs, int last) {
        if (last < 0) {
            return 1;
        }
        if (last == 0) {
            return 1;
        }
        char value = inputs[last];
        int count = 0;
        if (value > '0') {
            count = backTrace91(inputs, last - 1);
            char preValue = inputs[last - 1];
            if ((preValue == '1') || (value <= '6' && preValue == '2')) {
                count += backTrace91(inputs, last - 2);
            }
        } else {
            char preValue = inputs[last - 1];
            if ((preValue == '1' || preValue == '2')) {
                count += backTrace91(inputs, last - 2);
            } else {
                count = 0;
            }
        }
        return count;
    }

    /**
     * Q22: 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backTrace22("", 0, 0, n, result);
        return result;
    }

    public static void backTrace22(String result, int open, int close, int n, List<String> res) {
        if (result.length() == 2 * n) {
            res.add(result.toString());
            return;
        }
        if (open < n) {
            backTrace22(result + "(", open + 1, close, n, res);
        }
        if (close < open) {
            backTrace22(result + ")", open, close + 1, n, res);
        }
    }

    /**
     * Q46. 全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        backTrace46(deque, nums, results);
        return results;
    }

    public static void backTrace46(Deque<Integer> deque, int[] nums, List<List<Integer>> results) {
        if (deque.size() == nums.length) {
            results.add(new ArrayList<>(deque));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!deque.contains(nums[i])) {
                deque.add(nums[i]);
                backTrace46( deque, nums, results);
                deque.removeLast();
            }
        }
    }

}
