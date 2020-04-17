package slidewindow;


public class Test {
    public static void main(String[] args){
        testMaxArea();
    }

    private static void testLengthOfLongestSubStr() {
        String content = "pwwwkew";
        int result = Solutions.lengthOfLongestSubString2(content);
        System.out.println("content: " + content +"; result: " + result);
    }

    private static void testMaxArea(){
        int [] area = {2,3,10,5,7,8,9};
        int res = Solutions.maxArea(area);
        System.out.println("testMaxArea result: " + res);
    }
}
