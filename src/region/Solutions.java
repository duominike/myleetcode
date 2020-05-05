package region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solutions {
    /**
     * Q56: 给出一个区间的集合，请合并所有重叠的区间。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     * <p>
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] prev = null;
        List<int[]> res = new ArrayList<>();
        for (int[] curr : intervals) {
            if (prev == null || curr[0] > prev[1]) {
                prev = curr;
                res.add(prev);
            } else {
                prev[1] = Math.max(prev[1], curr[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * Q456: 定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * <p>
     * 注意:
     * <p>
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * 示例 1:
     * <p>
     * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
     * <p>
     * 输出: 1
     * <p>
     * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
     * 示例 2:
     * <p>
     * 输入: [ [1,2], [1,2], [1,2] ]
     * <p>
     * 输出: 2
     * <p>
     * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
     * 示例 3:
     * <p>
     * 输入: [ [1,2], [2,3] ]
     * <p>
     * 输出: 0
     * <p>
     * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 将所有区间按照起始时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 用一个变量 end 记录当前的最小结束时间点，以及一个 count 变量记录到目前为止删除掉了多少区间
        int end = intervals[0][1];
        int count = 0;

        // 从第二个区间开始，判断一下当前区间和前一个区间的结束时间
        for (int i = 1; i < intervals.length; i++) {
            // 当前区间和前一个区间有重叠，即当前区间的起始时间小于上一个区间的结束时间，end记录下两个结束时间的最小值，把结束时间晚的区间删除，计数加1。
            if (intervals[i][0] < end) {
                end = Math.min(end, intervals[i][1]);
                count++;
            } else {
                end = intervals[i][1];
            }
        }

        // 如果没有发生重叠，根据贪婪法，更新 end 变量为当前区间的结束时间
        return count;
    }
}
