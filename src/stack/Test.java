package stack;


public class Test {
    public static void main(String[] args) {
//        testLongestValidParentheses();
        int [] heights = {2,1,5,6,2,3};
        //Solutions.largestRectangleArea(heights);
        Solutions.calculate("1-11");
    }

    private static void testLongestValidParentheses(){
        String str = ")()())";
        int ans = Solutions.longestValidParentheses(str);
        System.out.println(str + ": valid Length: " + ans);
    }
}
