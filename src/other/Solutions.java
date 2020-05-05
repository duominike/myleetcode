package other;

public class Solutions {
    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int i = 0;
        int j = 0;
        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;
        int midIndex = (nums1.length + nums2.length + 1) / 2;
        double ans = 0;
        while (low <= high) {
            i = (high + low) / 2;
            j = midIndex - i;
            if (i < high && nums2[j - 1] > nums1[i]) {
                low = low + 1;
            } else if (i > low && nums1[i - 1] > nums2[j]) {
                high = i - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return ans;
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length < 2) {
            return nums[0] == target ? 0 : -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            if (nums[low] == target) {
                return low;
            }
            if (nums[high] == target) {
                return high;
            }
            low++;
            high--;
        }
        return -1;
    }

    /**
     * 切分法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int k = (m + n) / 2;
        if ((m + n) % 2 == 1) {
            return findKth(nums1, 0, m - 1, nums2, 0, n - 1, k + 1);
        } else {
            return (findKth(nums1, 0, m - 1, nums2, 0, n - 1, k) +
                    findKth(nums1, 0, m - 1, nums2, 0, n - 1, k + 1)) / 2.0;
        }
    }

    public static int findKth(int[] nums1, int l1, int h1, int[] nums2, int l2, int h2, int k) {
        int m = h1 - l1 + 1;
        int n = h2 - l2 + 1;
        if (m > n) {
            return findKth(nums2, l2, h2, nums1, l1, h1, k);
        }
        if (m == 0) {
            return nums2[l2 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[l1], nums2[l2]);
        }
        int na = Math.min(k / 2, m);
        int nb = k - na;
        int va = nums1[l1 + na - 1];
        int vb = nums2[l2 + nb - 1];
        if (va == vb) {
            return va;
        } else if (va < vb) {
            return findKth(nums1, l1 + na, h1, nums2, l2, l2 + nb - 1, k - na);
        } else {
            return findKth(nums1, l1, l1 + na - 1, nums2, l2 + nb, h2, k - nb);
        }
    }

    /**
     * Q42: 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[0];
        int rightMax = height[right];
        int ans = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                ans += leftMax - height[left];
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        if (n == 2) {
            return "11";
        }
        String prev = countAndSay(n - 1);
        int slow = 0;
        int fast = 1;
        StringBuilder res = new StringBuilder();
        while (fast < prev.length()) {
            if (prev.charAt(fast) == prev.charAt(slow)) {
                if (fast == prev.length() - 1) {
                    res.append(fast - slow + 1);
                    res.append(prev.charAt(fast));
                }
                fast++;
            } else {
                res.append(fast - slow);
                res.append(prev.charAt(slow));
                slow = fast;
                if (fast < prev.length() - 1) {
                    fast++;
                }
            }
        }
        return res.toString();

    }
}
