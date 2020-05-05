package stroperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solutions {
    /**
     * z 形变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        int rows = Math.min(numRows, s.length());
        int rowIndex = 0;
        boolean isToDown = true;
        String[] resArray = new String[rows];
        for (int i = 0; i < rows; i++) {
            resArray[i] = "";
        }
        for (char chr : s.toCharArray()) {
            if (isToDown) {
                resArray[rowIndex++] += chr;
            } else {
                resArray[rowIndex--] += chr;
            }
            if (rowIndex == rows) {
                isToDown = false;
                rowIndex = rows - 2;
            }
            if (rowIndex < 0) {
                isToDown = true;
                rowIndex = 1;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : resArray) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * 说明：
     * <p>
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> results = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] counts = new int[26];
            for (int j = 0; j < str.length(); j++) {
                counts[str.charAt(j) - 'a']++;
            }
            StringBuilder keyBuilder = new StringBuilder();
            for (int k = 0; i < counts.length; k++) {
                if (counts[k] == 0) {
                    continue;
                }
                keyBuilder.append((char) ('a' + k)).append(counts[k]);
            }
            String key = keyBuilder.toString();
            if (!results.containsKey(key)) {
                List<String> strings = new ArrayList<>();
                strings.add(str);
                results.put(key, strings);
            } else {
                results.get(key).add(str);
            }
        }
        return new ArrayList<>(results.values());
    }

    /**
     * Q5: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * Method1: 中心扩展法
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    // todo 动态规划
    public static String longetPalidrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int start = 0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    int currlen = j - i + 1;
                    if (currlen > maxLen) {
                        start = i;
                        maxLen = currlen;
                    }
                }
            }
        }
        return s.substring(start, maxLen + start);
    }

    /**
     * Q28: 实现 strStr() 函数。
     * <p>
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) {
            return 0;
        }
        int[] LPS = getLPS(needle);
        int i = 0;
        int j = 0;
        while (i < m) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == n) {
                    return i - j;
                }
            } else if (j > 0) {
                j = LPS[j - 1];
            } else {
                i++;
            }
        }
        return -1;
    }

    public int[] getLPS(String needle) {
        int[] lps = new int[needle.length()];
        int i = 1;
        int len = 0;
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                i++;
            }
        }
        return lps;
    }


}
