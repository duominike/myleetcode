package stroperation;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    /**
     * z 形变换
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        int rows = Math.min(numRows, s.length());
        int rowIndex = 0;
        boolean isToDown = true;
        String[] resArray = new String[rows];
        for (int i = 0; i < rows; i++) {
            resArray[i] = "";
        }
        for (char chr : s.toCharArray()) {
            if (isToDown) {
                resArray[rowIndex++] += chr;
            } else {
                resArray[rowIndex--] += chr;
            }
            if (rowIndex == rows) {
                isToDown = false;
                rowIndex = rows - 2;
            }
            if (rowIndex < 0) {
                isToDown = true;
                rowIndex = 1;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : resArray) {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
