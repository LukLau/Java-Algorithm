package org.learn.algorithm.datastructure;

import java.util.Stack;

/**
 * 使用两个
 *
 * @author luk
 * @date 2021/4/16
 */
public class MyQueue {

    private final Stack<Integer> pushStack;

    private final Stack<Integer> popStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        pushStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (!popStack.isEmpty()) {
            return popStack.pop();
        }
        while (!pushStack.isEmpty()) {
            Integer pop = pushStack.pop();
            popStack.push(pop);
        }
        return popStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (!popStack.isEmpty()) {
            return popStack.peek();
        }
        while (!pushStack.isEmpty()) {
            Integer pop = pushStack.pop();
            popStack.push(pop);
        }
        return popStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return popStack.isEmpty() && !pushStack.isEmpty();
    }

}
