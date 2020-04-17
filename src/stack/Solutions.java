package stack;

import java.util.ArrayDeque;
import java.util.Deque;

class Solutions {
    /**
     * 出入堆栈
     * 是否为有效括号
     * @param s
     * @return
     */
    public static boolean isValidStr(String s){
        if(s == null || s.length() == 0){
            return true;
        }
        ArrayDeque<Character> stack = new ArrayDeque<>();
        char indexChar ,head;
        for(int i = 0; i< s.length(); i++){
            indexChar = s.charAt(i);
            if(indexChar == '(' || indexChar== '[' || indexChar == '{'){
                stack.add(indexChar);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                head = stack.getLast();
                if((head == '(' && indexChar == ')')
                        || (head == '{' && indexChar == '}')
                        || (head == '[' && indexChar == ']')){
                    stack.pollLast();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /** Q32. 最长有效括号
     * 堆栈入索引：栈为空，将要出堆栈的索引先入队，遍历过程中求结果
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        Deque<Integer> indexs = new ArrayDeque<>();
        indexs.add(-1);
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i) == '('){
                indexs.add(i);
            }else{
                indexs.pollLast();
                if(indexs.isEmpty()){
                    indexs.add(i);
                }else{
                    ans = Math.max(ans, i - indexs.getLast());
                }
            }
        }
        return ans;

    }
}
