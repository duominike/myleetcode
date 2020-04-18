package slidewindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solutions {
    /**
     * Q3. 无重复字符的最长子串：
     *
     * @param content
     * @return
     */
    public static int lengthOfLongestSubString1(String content) {
        if (content == null || content.equals("")) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int length = right - left + 1;
        for (int i = 1; i < content.length(); i++) {
            for (int j = left; j < i; j++) {
                if (content.charAt(j) == content.charAt(i)) {
                    left = j + 1;
                    break;
                }
            }
            if (left > right) {
                right = left;
            } else {
                right = i;
            }
            int tempLength = right - left + 1;
            if (tempLength > length) {
                length = tempLength;
            }
        }
        return length;
    }

    /**
     * * Q3. 无重复字符的最长子串：
     * * HashMap判断是否与之前的字符重复
     */

    public static int lengthOfLongestSubString2(String content) {
        if (content == null || content.equals("")) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int len = left - right + 1;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < content.length()) {
            Integer index = map.get(content.charAt(right));
            map.put(content.charAt(right), right);
            if (index != null && index >= left) {
                left = index + 1;
            }
            if (right - left + 1 > len) {
                len = right - left + 1;
            }
            right++;
        }
        return len;
    }

    /**
     * Q11: 双指针向中间遍历
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * [2,3,10,5,7,8,9]-> 36
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int low = 0;
        int high = height.length - 1;
        int res = 0;
        while (low < high) {
            int current = Math.abs(low - high) * Math.min(height[low], height[high]);
            if (current > res) {
                res = current;
            }
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return res;
    }

    /**
     * Q3: 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     */

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return null;
        }
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        int left;
        int right;
        for (int i = 0; i < nums.length; i++) {
            left = i + 1;
            right = nums.length - 1;
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    results.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++; // 去重
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--; // 去重
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                }
            }
        }
        return results;
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
     * 假定每组输入只存在唯一答案。
     * <p>
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * <p>
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int result = nums[start] + nums[end] + nums[i];
                if (Math.abs(result - target) < Math.abs(res - target)) {
                    res = result;
                }
                if (res > target) {
                    end--;
                } else if (res < target) {
                    start++;
                } else {
                    return res;
                }

            }
        }
        return res;
    }

    /**
     * Q26. 删除排序数组中的重复项
     * nums = [0,0,1,1,1,2,2,3,3,4],
     */

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 1;
        }
        int curr = 0;
        int latestRepeatIndex = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[curr]) {
                if (latestRepeatIndex < curr) {
                    latestRepeatIndex = i;
                }
            } else {
                if (latestRepeatIndex > curr) {
                    nums[latestRepeatIndex] = nums[i];
                    curr = latestRepeatIndex;
                    latestRepeatIndex++;
                } else {
                    curr = i;
                }
            }
        }
        return curr + 1;
    }

    /**
     * Q121: 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意：你不能在买入股票前卖出股票。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length - 1; i++) {
            if (prices[i] > prices[i - 1]) {
                ans = Math.max(ans, prices[i] - min);
            } else {
                min = Math.min(min, prices[i]);
            }
        }
        return ans;
    }
}
