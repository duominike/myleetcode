package binarySearch;

public class Test {
    public static void main(String[] args) {
        int[] result = Solutions.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println("result first pos: " + result[0] + "; last: " + result[1]);
    }
}
