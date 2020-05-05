package binarySearch;

import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

public class Solutions {
    /**
     * Q33: 搜索旋转排序数组
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * <p>
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * <p>
     * 你可以假设数组中不存在重复的元素。
     * <p>
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        // 最左侧元素下标
        int left = 0;
        // 最右侧元素下标
        int right = arr.length - 1;
        while (left <= right) {
            // 中间元素下标
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            // 情况1:如果中间元素在旋转点左侧
            if (arr[mid] >= arr[left]) {
                //target 如果位于中间元素的左侧
                if (arr[mid] > target && target >= arr[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Q34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int leftIndex = searchTargetIndex(nums, target, true);

        if (leftIndex == nums.length || nums[leftIndex] != target) {
            return result;
        }
        result[0] = leftIndex;
        result[1] = searchTargetIndex(nums, target, false) - 1;
        return result;
    }

    public static int searchTargetIndex(int nums[], int target, boolean isLeft) {
        int lo = 0;
        int high = nums.length;
        while (lo < high) {
            int mid = (lo + high) / 2;
            if (nums[mid] > target || (isLeft && nums[mid] == target)) {
                high = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * Q81: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     * <p>
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: nums = [2,5,6,0,0,1,2], target = 3
     * 输出: false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return false;
    }

    /**
     * Q875: 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * <p>
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，
     * 她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
     * <p>
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * <p>
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     * 示例 2：
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     * 示例 3：
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 6
     * 输出: 23
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int right = getMax(piles) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(mid, piles, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean canFinish(int speed, int[] piles, int H) {
        int times = 0;
        for (int i = 0; i < piles.length; i++) {
            times += timesof(speed, piles[i]);
        }
        return times <= H;
    }

    public int timesof(int speed, int pile) {
        return (pile / speed) + (pile % speed > 0 ? 1 : 0);

    }

    public int getMax(int[] piles) {
        int max = piles[0];
        for (int i = 1; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }


    /**
     * Q1011. 在 D 天内送达包裹的能力
     * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     *
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     *
     *
     * 示例 1：
     *
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
     * 输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     *
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     * 示例 2：
     *
     * 输入：weights = [3,2,2,4,1,4], D = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = getMaxWeights(weights);
        int right = getSum(weights) +1;
        while(left < right){
            int mid = left + (right -left)/2;
            if(canFinish(weights, D, mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    public int getMaxWeights(int[] weights) {
        int max = weights[0];
        for (int i = 1; i < weights.length; i++) {
            max = Math.max(max, weights[i]);
        }
        return max;
    }
    public int getSum(int[] weights) {
        int sum = weights[0];
        for (int i = 1; i < weights.length; i++) {
            sum += weights[i];
        }
        return sum;
    }

    public boolean canFinish(int [] weights, int D, int cap){
        int j = 0;
        for(int i = 0; i< D; i++){
            int maxCap = cap;
            while((maxCap -= weights[j]) >= 0){
                j++;
                if(j == weights.length){
                    return true;
                }
            }
        }
        return false;
    }


}

