package sort;

import java.util.Random;

public class Sorts {
    public int[] selectSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int min = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] < num[min]) {
                    min = j;
                }
            }
            int tmp = num[min];
            num[min] = num[i];
            num[i] = tmp;
        }
        return num;
    }

    public int[] insertSort(int[] num) {
        if (num == null || num.length < 2) {
            return num;
        }
        for (int i = 1; i < num.length; i++) {
            int temp = num[i];
            int j = i - 1;
            while (j >= 0 && num[j] > temp) {
                num[j + 1] = num[j];
                j--;
            }
            num[j + 1] = temp;
        }
        return num;
    }

    public int[] bubbleSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            boolean flag = true;
            for (int j = 0; j < num.length - 1 - i; j++) {
                if (num[j] > num[j + 1]) {
                    flag = false;
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
            if (flag) {
                break;
            }
        }
        return num;
    }

    public int[] quickSort(int[] num, int left, int right) {
        if (left < right) {
            int mid = partition(num, left, right);
            quickSort(num, left, mid - 1);
            quickSort(num, mid + 1, right);
        }
        return num;
    }

    public int partition(int[] num, int left, int right) {
        int i = left + 1;
        int j = right;
        int pivot = num[left];
        while (true) {
            while (i <= j && num[i] <= pivot) {
                i++;
            }

            while (i <= j && num[j] >= pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        num[left] = num[j];
        num[j] = pivot;
        return j;
    }


    /**
     * 215: 在未排序数组中找到第K大的数。第n-k小的数
     *
     * @param left
     * @param right
     * @param nums
     * @param k
     * @return
     */
    public static int quickFindKthSmallest(int left, int right, int[] nums, int k) {
        if (left == right) {
            return nums[left];
        }
        Random random = new Random();
        int privot_index = left + random.nextInt(right - left);
        privot_index = partition215(left, right, privot_index, nums);
        if (privot_index == k) {
            return nums[privot_index];
        } else if (privot_index < k) {
            return quickFindKthSmallest(privot_index + 1, right, nums, k);
        } else {
            return quickFindKthSmallest(left, privot_index - 1, nums, k);
        }
    }

    public static void swap215(int left, int right, int nums[]) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static int partition215(int left, int right, int pivot_index, int nums[]) {
        int pivot = nums[pivot_index];
        swap215(pivot_index, right, nums);
        int store_index = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap215(store_index, i, nums);
                store_index++;
            }
        }
        swap215(store_index, right, nums);
        return store_index;
    }

    /**
     * Q51: 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [7,5,6,4]
     * 输出: 5
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */

    public int reversePairs(int nums[]){
        int len = nums.length;
        if(len < 2){
            return 0;
        }
        int [] copy = new int[len];
        for(int i= 0; i<len; i++){
            copy[i] = nums[i];
        }
        int [] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    public int reversePairs(int[] nums, int left, int right, int[] temp){
        if(left == right){
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftCnt = reversePairs(nums, left, mid, temp);
        int rightCnt = reversePairs(nums, mid +1, right, temp);
        if(nums[mid] <= nums[mid + 1]){
            return leftCnt + rightCnt;
        }
        return leftCnt + rightCnt + mergeAndCount(nums, left, mid, right, temp);
    }

    public int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp){
        for(int i = left; i<=right; i++) {
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;
        int count = 0;
        for(int k = left; k<=right; k++){
            if(i == mid + 1){
                nums[k] = temp[j];
                j++;
            }else if(j == right +1){
                nums[k] = temp[i];
                i++;
            }else if(temp[i] <= temp[j]){
                nums[k] = temp[i];
                i++;
            }else{
                nums[k] = temp[j];
                j++;
                count += (mid - i +1);
            }
        }
        return count;
    }


}
