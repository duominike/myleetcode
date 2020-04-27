package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    private LinkedList<Integer> stack = new LinkedList<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.add(x);
            stack.add(x);
        } else {
            int tmp = stack.getLast();
            stack.add(x);
            if (tmp < x) {
                stack.add(tmp);
            } else {
                stack.add(x);
            }
        }
    }

    public void pop() {
        stack.pollLast();
        stack.pollLast();
    }

    public int top() {
        return stack.get(stack.size() - 2);
    }

    public int getMin() {
        return stack.getLast();
    }
}
