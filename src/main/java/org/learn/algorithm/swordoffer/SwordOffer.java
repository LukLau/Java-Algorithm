package org.learn.algorithm.swordoffer;

import org.learn.algorithm.datastructure.ListNode;
import org.learn.algorithm.datastructure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author luk
 * @date 2021/5/12
 */
public class SwordOffer {

    public static void main(String[] args) {
        ListNode dummy = new ListNode(1);
        dummy.next = new ListNode(2);

        dummy.next.next = new ListNode(3);

        dummy.next.next.next = new ListNode(4);

        SwordOffer offer = new SwordOffer();
        offer.printListFromTailToHead(dummy);
    }

    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        int row = array.length;
        int column = array[0].length;
        int i = row - 1;
        int j = 0;
        while (i >= 0 && j < column) {
            int val = array[i][j];
            if (val == target) {
                return true;
            } else if (val < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串
     * @return string字符串
     */
    public String replaceSpace(String s) {
        // write code here
        if (s == null || s.isEmpty()) {
            return "";
        }
        String[] words = s.split(" ", -1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(words[i]);
            if (i != words.length - 1) {
                builder.append("%20");
            }
        }
        return builder.toString();
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> integers = printListFromTailToHead(listNode.next);

        integers.add(listNode.val);

        ArrayList<Integer> result = new ArrayList<>(integers);

        return result;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        return constructTree(0, pre, 0, in.length - 1, in);
    }

    private TreeNode constructTree(int preStart, int[] pre, int inStart, int inEnd, int[] in) {
        if (preStart == pre.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == root.val) {
                index = i;
                break;
            }
        }
        root.left = constructTree(preStart + 1, pre, inStart, index - 1, in);
        root.right = constructTree(preStart + index - inStart + 1, pre, index + 1, inEnd, in);
        return root;
    }


    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return array[left];
    }


    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(i);
            while (!stack.isEmpty() && pushA[stack.peek()] == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {

            TreeNode poll = queue.poll();
            result.add(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return result;
    }


    public boolean VerifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return intervalVerify(sequence, 0, sequence.length - 1);
    }

    private boolean intervalVerify(int[] sequence, int start, int end) {
        if (start == end) {
            return true;
        }
        if (start > end) {
            return false;
        }
        int leftIndex = start;
        while (leftIndex < end && sequence[leftIndex] < sequence[end]) {
            leftIndex++;
        }
        int rightIndex = leftIndex;
        while (rightIndex < end && sequence[rightIndex] > sequence[end]) {
            rightIndex++;
        }
        if (rightIndex != end) {
            return false;
        }
        return intervalVerify(sequence, start, leftIndex - 1) && this.intervalVerify(sequence, leftIndex, end - 1);
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        intervalPath(result, new ArrayList<>(), root, target);
        return result;
    }

    private void intervalPath(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> tmp, TreeNode root, int target) {
        tmp.add(root.val);
        if (root.left == null && root.right == null && root.val == target) {
            result.add(new ArrayList<>(tmp));
            return;
        } else {
            if (root.left != null) {
                intervalPath(result, tmp, root.left, target - root.val);
            }
            if (root.right != null) {
                intervalPath(result, tmp, root.right, target - root.val);
            }
        }
        tmp.remove(tmp.size() - 1);
    }


}
