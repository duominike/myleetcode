package stack;


public class Test {
    public static void main(String[] args) {
        testLongestValidParentheses();
    }

    private static void testLongestValidParentheses(){
        String str = ")()())";
        int ans = Solutions.longestValidParentheses(str);
        System.out.println(str + ": valid Length: " + ans);
    }
}
