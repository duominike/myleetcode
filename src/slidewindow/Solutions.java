package slidewindow;

import java.util.HashMap;

class Solutions {
    /**
     *  Q3. 无重复字符的最长子串：
     * @param content
     * @return
     */
    public static int lengthOfLongestSubString1(String content){
        if(content == null || content.equals("")){
            return 0;
        }
        int left = 0;
        int right = 0;
        int length = right - left +1;
        for(int i = 1; i< content.length(); i++){
            for(int j = left; j < i; j++){
                if(content.charAt(j) == content.charAt(i)){
                    left = j+1;
                    break;
                }
            }
            if(left > right){
                right = left;
            }else{
                right = i;
            }
            int tempLength = right - left +1;
            if(tempLength > length){
                length = tempLength;
            }
        }
        return length;
    }

    /**
     *  * Q3. 无重复字符的最长子串：
     * * HashMap判断是否与之前的字符重复
     */

    public static int lengthOfLongestSubString2(String content){
        if(content == null || content.equals("")){
            return 0;
        }
        int left = 0;
        int right = 0;
        int len = left - right +1;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < content.length()){
            Integer index = map.get(content.charAt(right));
            map.put(content.charAt(right), right);
            if(index != null && index >= left){
                left = index + 1;
            }
            if(right - left + 1 > len){
                len = right -left +1;
            }
            right++;
        }
        return len;
    }

    /** Q11: 双指针向中间遍历
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * [2,3,10,5,7,8,9]-> 36
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        if(height == null || height.length < 2){
            return 0;
        }
        int low = 0;
        int high = height.length - 1;
        int res = 0;
        while (low < high){
            int current = Math.abs(low - high) * Math.min(height[low], height[high]);
            if(current > res){
                res = current;
            }
            if(height[low] < height[high]){
                low++;
            }else{
                high--;
            }
        }
        return res;
    }
}
