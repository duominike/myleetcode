package number;

public class Resolutions {
    /**
     * Q7:给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
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
     * @param num1 1234
     * @param num2 111
     * @return
     */
    public static String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) {
            return multiply(num2, num1);
        }
        int[] res = new int[num1.length() + num2.length()];
        int[][] calc = new int[num2.length()][num1.length() + num2.length() - 1];
        for (int i = num2.length() - 1; i >= 0; i--) {
            int a = Integer.parseInt(String.valueOf(num2.charAt(i)));
            for (int j = 0; j < num1.length(); j++) {
                int b = Integer.parseInt(String.valueOf(num1.charAt(j)));
                calc[num2.length() - 1 - i][j + i] = a * b;
            }
        }
        for (int i = 0; i < num1.length() + num2.length() - 1; i++) {
            for (int j = 0; j < num2.length(); j++) {
                res[i + 1] += calc[j][i];
            }
        }
        for (int i = res.length - 1; i > 0; i--) {
            if (res[i] >= 10) {
                int val = res[i];
                res[i - 1] = res[i - 1] + val / 10;
                res[i] = val % 10;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            stringBuilder.append(res[i]);
        }
        String newStr = stringBuilder.toString().replaceAll("^(0+)", "");
        if (newStr.equals("")) {
            return "0";
        } else {
            return newStr;
        }
    }

}
