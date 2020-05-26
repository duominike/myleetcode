package number;

public class Resolutions {
    /**
     * Q7:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 处理越界问题
     *
     * @param n
     * @return
     */
    public static int reverse(int n) {
        int rs = 0;
        while (true) {
            int y = n % 10;
            n = n / 10;
            if (rs * 10 / 10 != rs) {
                return 0;
            }
            rs = rs * 10 + y;
            if (n == 0) {
                break;
            }
        }
        return rs;
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * method1
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String num = String.valueOf(x);
        if (num.length() == 1) {
            return true;
        }

        int low = 0;
        int high = num.length() - 1;
        while (low < high) {
            if (num.charAt(low) != num.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

    /**
     * Q9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * method2
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }

        if (x % 10 == 0) {
            return false;
        }
        int rs = 0;
        while (rs < x / 10) {
            int y = x % 10;
            x = x / 10;
            rs = rs * 10 + y;
            if (rs == x) {
                return true;
            } else if (x / 10 == rs) {
                return true;
            }
        }
        return false;
    }

    /**
     * Q43. 字符串相乘
     * :给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * num1 和 num2 的长度小于110。
     * num1 和 num2 只包含数字 0-9。
     * num1 和 num2 均不以零开头，除非是数字 0 本身。
     * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/multiply-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param num1 1234
     * @param num2 111
     * @return
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + n1 * n2;
                res[i + j + 1] = sum % 10;
                res[i + j] = res[i + j] + sum / 10;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            stringBuilder.append(res[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * xyz = 100 *x + 10 *y + z = 99*x + 9*y + x +y +z
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if (num % 9 == 0 && num != 0) {
            num = 9;
        } else {
            num = num % 9;
        }
        return num;
    }

    /**
     * Q50. Pow(x, n)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        double half = myPow(x, n / 2);
        double rest = myPow(x, n % 2);
        return rest * half * half;
    }

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/integer-to-roman
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int temp = 100;
        while (num >= 10 * temp) {
            stringBuilder.append("M");
            num -= 10 * temp;
        }
        if (num >= 9 * temp) {
            stringBuilder.append("CM");
            num -= 9 * temp;
        }
        if (num >= 5 * temp) {
            stringBuilder.append("D");
            num -= 5 * temp;
        }
        if (num >= 4 * temp && num < 5 * temp) {
            stringBuilder.append("CD");
            num -= 4 * temp;
        }
        while (num >= temp) {
            stringBuilder.append("C");
            num -= temp;
        }
        temp = temp / 10;

        if (num >= 9 * temp) {
            stringBuilder.append("XC");
            num -= 9 * temp;
        }
        if (num >= 5 * temp) {
            stringBuilder.append("L");
            num -= 5 * temp;
        }

        if (num >= 4 * temp && num < 5 * temp) {
            stringBuilder.append("XL");
            num -= 4 * temp;
        }

        if (num >= 4 * temp && num < 5 * temp) {
            stringBuilder.append("XL");
            num -= 4 * temp;
        }

        while (num >= temp) {
            stringBuilder.append("X");
            num -= 10;
        }

        if (num >= 9) {
            stringBuilder.append("IX");
            num -= 9;
        }
        if (num >= 5) {
            stringBuilder.append("V");
            num -= 5;
        }
        if (num >= 4 && num < 5) {
            stringBuilder.append("IV");
            num -= 4;
        }
        while (num >= 1) {
            stringBuilder.append("I");
            num -= 1;
        }
        return stringBuilder.toString();
    }

    /**
     * Q80: 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1:
     * <p>
     * 给定 nums = [1,1,1,2,2,3],
     * <p>
     * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * <p>
     * 给定 nums = [0,0,1,1,1,1,2,3,3],
     * <p>
     * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length < 3) {
            return nums.length;
        }

        int beginIndex = 0;
        int left = 0;
        int mid = 1;
        int right = 2;

        while (right < nums.length) {
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                for (int i = right; i > beginIndex; i--) {
                    nums[i] = nums[i - 1];
                }
                beginIndex++;
            }
            left++;
            mid++;
            right++;
        }
        if (beginIndex > 0) {
            for (int i = beginIndex; i < nums.length; i++) {
                nums[i - beginIndex] = nums[i];
            }
        }
        return nums.length - beginIndex;
    }

    /**
     * Q88: 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * <p>
     *  
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *  
     * <p>
     * 示例:
     * <p>
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * 输出: [1,2,2,3,5,6]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int i = 0;
        int j = 0;
        int offset = 0;
        while (j < n && i < m + offset) {
            if (nums2[j] < nums1[i]) {
                for (int k = m - 1 + offset; k >= i; k--) {
                    nums1[k + 1] = nums1[k];
                }
                nums1[i] = nums2[j];
                offset++;
                j++;
            }
            i++;
        }
        while (j < n) {
            nums1[i++] = nums2[j++];
        }
    }

    public static String addBinary(String a, String b) {
        if (b == null || b.equals("")) {
            return a;
        }

        if (a == null || a.equals("")) {
            return a;
        }
        int len = Math.max(a.length(), b.length()) + 1;
        int[] res = new int[len];
        int i = a.length() - 1;
        int j = b.length() - 1;
        int index = len - 1;
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();
        while (i >= 0 || j >= 0) {
            int value = 0;
            if (i < 0) {
                value = charsB[j] - '0';
                j--;
            } else if (j < 0) {
                value = charsA[i] - '0';
                i--;
            } else {
                value = (charsA[i] - '0') + (charsB[j] - '0');
                i--;
                j--;
            }
            value = res[index] + value;
            if (value > 1) {
                res[index] = value % 2;
                res[index - 1] = 1;
            } else {
                res[index] = value;
            }
            index--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int k = 0; k < res.length; k++) {
            if (k == 0 && res[k] == 0) {
                continue;
            }
            stringBuilder.append(res[k]);
        }
        return stringBuilder.toString();

    }

    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n = n & (n - 1);
        }
        return sum;
    }

}
