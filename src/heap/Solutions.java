package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class Solutions {
    public static void swap(int left, int right, int nums[]) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static int partition(int left, int right, int pivot_index, int nums[]) {
        int pivot = nums[pivot_index];
        swap(pivot_index, right, nums);
        int store_index = left;
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(store_index, i, nums);
                store_index++;
            }
        }
        swap(store_index, right, nums);
        return store_index;
    }

    public static int quickFindKthSmallest(int left, int right, int[] nums, int k) {
        if (left == right) {
            return nums[left];
        }
        Random random = new Random();
        int privot_index = left + random.nextInt(right - left);
        privot_index = partition(left, right, privot_index, nums);
        if (privot_index == k) {
            return nums[privot_index];
        } else if (privot_index < k) {
            return quickFindKthSmallest(privot_index + 1, right, nums, k);
        } else {
            return quickFindKthSmallest(left, privot_index - 1, nums, k);
        }
    }

    /**
     * 215. 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * [3,2,1,5,6,4]
     *
     * @param nums
     * @param
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        return quickFindKthSmallest(0, nums.length - 1, nums, nums.length - k);
    }

    public static int findKthLargestByHeap(int[] nums, int k){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 -o2;
            }
        });
        for(int i = 0; i < nums.length; i++){
            priorityQueue.add(nums[i]);
            if(priorityQueue.size() > k){
                priorityQueue.poll();
            }
        }
        return priorityQueue.poll();
    }
}
