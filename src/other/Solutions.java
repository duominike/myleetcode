package other;

public class Solutions {
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
}
