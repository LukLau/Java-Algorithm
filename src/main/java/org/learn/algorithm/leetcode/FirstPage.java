package org.learn.algorithm.leetcode;

import org.learn.algorithm.datastructure.ListNode;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.thymeleaf.expression.Strings;

import javax.print.DocFlavor;
import java.util.*;

/**
 * @author luk
 * @date 2021/4/6
 */
public class FirstPage {

    public static void main(String[] args) {
        FirstPage page = new FirstPage();
//        page.simplifyPath("/home/");

//        System.out.println(page.intToRoman(58));
//        page.myPow(2, 10);
        int[] heights = new int[]{2, 4};
//        page.largestRectangleArea(heights);
        page.restoreIpAddresses("0000");
    }

    /**
     * 1. Two Sum
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    /**
     * 2. Add Two Numbers
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        int carry = 0;
        ListNode root = new ListNode(0);
        ListNode dummy = root;

        while (l1 != null || l2 != null || carry != 0) {
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            ListNode node = new ListNode(val % 10);
            carry = val / 10;

            dummy.next = node;
            dummy = dummy.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return root.next;
    }

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = result * 10 + x % 10;

            x /= 10;
        }
        return result;
    }


    /**
     * 17. Letter Combinations of a Phone Number
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        String[] combine = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> result = new LinkedList<>();
        result.offer("");
        int size = digits.length();
        for (int i = 0; i < size; i++) {
            char digit = digits.charAt(i);
            int index = Character.getNumericValue(digit);
            String words = combine[index];

            while (!result.isEmpty() && result.peek().length() == i) {
                String poll = result.poll();
                for (char tmp : words.toCharArray()) {
                    result.offer(poll + tmp);
                }
            }
        }
        return result;
    }

    /**
     * 8. String to Integer (atoi)
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s == null) {
            return 0;
        }
        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        int len = s.length();

        int sign = 1;

        char[] words = s.toCharArray();

        int index = 0;

        if (words[index] == '+' || words[index] == '-') {
            sign = words[index] == '+' ? 1 : -1;
            index++;
        }
        long result = 0;
        while (index < words.length && Character.isDigit(words[index])) {
            int value = Character.getNumericValue(words[index]);
            result = result * 10 + value;
            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            index++;
        }
        return Long.valueOf(result * sign).intValue();
    }


    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftEdge = 0;
        int rightEdge = 0;
        int result = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (leftEdge <= height[left]) {
                    leftEdge = height[left];
                } else {
                    result += leftEdge - height[left];
                }
                left++;
            } else {
                if (rightEdge <= height[right]) {
                    rightEdge = height[right];
                } else {
                    result += rightEdge - height[right];
                }
                right--;
            }
        }
        return result;
    }


    /**
     * 12. Integer to Roman
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String[] one = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] two = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] three = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] four = new String[]{"", "M", "MM", "MMM"};
        return four[num / 1000] + three[num % 1000 / 100] + two[num / 10 % 10] + one[num % 10];
    }

    /**
     * 13. Roman to Integer
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] words = s.toCharArray();
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            int val = map.get(words[i]);
            result += val;
            if (i > 0 && (map.get(words[i]) > map.get(words[i - 1]))) {
                result -= 2 * map.get(words[i - 1]);
            }
        }
        return result;
    }


    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length <= 2) {
            return -1;
        }
        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int val = nums[i] + nums[left] + nums[right];
                if (val == target) {
                    return target;
                } else if (val < target) {
                    left++;
                } else {
                    right--;
                }
                if (Math.abs(result - target) > Math.abs(val - target)) {
                    result = val;
                }
            }
        }
        return result;
    }


    /**
     * 32. Longest Valid Parentheses
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        char[] words = s.toCharArray();
        int result = 0;
        int left = -1;
        for (int i = 0; i < words.length; i++) {
            char tmp = words[i];
            if (tmp == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    left = i;
                }
                if (stack.isEmpty()) {
                    result = Math.max(result, i - left);
                } else {
                    result = Math.max(result, i - stack.peek());
                }
            }
        }
        return result;
    }

    public int longestValidParenthesesII(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();

        char[] words = s.toCharArray();

        int m = s.length();

        for (int i = 0; i < words.length; i++) {
            if (stack.isEmpty() || words[i] == '(') {

                stack.push(i);
            } else {
                if (words[stack.peek()] == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        if (stack.isEmpty()) {
            return s.length();
        }
        int result = 0;
        int end = s.length();
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            result = Math.max(result, end - pop - 1);
            end = pop;
        }
        result = Math.max(result, end);
        return result;
    }

    /**
     * 42. Trapping Rain Water
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        int leftEdge = 0;
        int rightEdge = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (leftEdge <= height[left]) {
                    leftEdge = height[left];
                } else {
                    result += leftEdge - height[left];
                }
                left++;
            } else {
                if (rightEdge <= height[right]) {
                    rightEdge = height[right];
                } else {
                    result += rightEdge - height[right];
                }
                right--;
            }
        }
        return result;
    }

    /**
     * 41. First Missing Positive
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swapValue(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swapValue(int[] tmp, int i, int j) {
        int val = tmp[i];
        tmp[i] = tmp[j];
        tmp[j] = val;
    }

    /**
     * 43. Multiply Strings
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int val = (Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue(num2.charAt(j))) + result[i + j + 1];

                result[i + j + 1] = val % 10;

                result[i + j] += val / 10;
            }
        }
        StringBuilder builder = new StringBuilder();

        for (int num : result) {
            if (!(num == 0 && builder.length() == 0)) {
                builder.append(num);
            }
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }

    /**
     * 48. Rotate Image
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                swapMatrix(matrix, i, j);
            }
        }
        for (int[] row : matrix) {
            swapRow(row, 0, row.length - 1);
        }
    }

    private void swapMatrix(int[][] matrix, int i, int j) {
        int val = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = val;
    }

    private void swapRow(int[] row, int start, int end) {
        for (int i = start; i <= (start + end) / 2; i++) {
            swap(row, i, end - i);
        }
    }


    /**
     * 49. Group Anagrams
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] words = str.toCharArray();

            Arrays.sort(words);

            String key = String.valueOf(words);

            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);

            map.put(key, list);

        }
        return new ArrayList<>(map.values());
    }


    public double myPow(double x, int n) {
        if (n == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return x % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);

    }


    /**
     * 56. Merge Intervals
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        LinkedList<int[]> result = new LinkedList<>();
        for (int[] interval : intervals) {
            if (!result.isEmpty() && result.peekLast()[1] >= interval[0]) {
                result.peekLast()[1] = Math.max(result.peekLast()[1], interval[1]);
            } else {
                result.offer(interval);
            }
        }
        return result.toArray(new int[][]{});
    }

    /**
     * 57. Insert Interval
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null) {
            return new int[][]{};
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        LinkedList<int[]> result = new LinkedList<>();
        int index = 0;
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            result.offer(intervals[index++]);
        }
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[index][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[index][1], newInterval[1]);
            index++;
        }
        result.offer(newInterval);
        while (index < intervals.length) {
            result.offer(intervals[index++]);
        }
        return result.toArray(new int[][]{});
    }

    /**
     * 60. Permutation Sequence
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        int[] pos = new int[n + 1];
        int base = 1;
        for (int i = 1; i <= n; i++) {
            base *= i;
            pos[i] = base;
        }
        StringBuilder builder = new StringBuilder();
        k--;
        for (int i = n - 1; i >= 0; i--) {
            int combine = pos[i];
            int index = k / combine;

            Integer num = nums.remove(index);

            builder.append(num.toString());
            k -= combine;
        }
        return builder.toString();
    }

    /**
     * 71. Simplify Path
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return "";
        }
        String[] words = path.split("/");

        LinkedList<String> result = new LinkedList<>();
        for (String word : words) {
            if (word.isEmpty() || word.equals("/")) {
                continue;
            } else if (word.equals("..") && !result.isEmpty()) {
                result.pollLast();
            } else if (!word.equals("..")) {
                result.offer(word);
            }
        }
        if (result.isEmpty()) {
            return "/";
        }
        String s = "";
        for (String tmp : result) {
            s = s + "/" + tmp;
        }
        return s;
    }

    /**
     * 75. Sort Colors
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int blue = nums.length - 1;
        int red = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] == 2 && i < blue) {
                swap(nums, i, blue--);
            }
            while (nums[i] == 0 && i > red) {
                swap(nums, i, red++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int val = nums[i];
        nums[i] = nums[j];
        nums[j] = val;
    }


    /**
     * 82. Remove Duplicates from Sorted List II
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            ListNode current = head.next;
            while (current != null && current.val == head.val) {
                current = current.next;
            }
            return deleteDuplicates(current);
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }

    /**
     * WC43 删除有序链表中重复的元素-I
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicatesII(ListNode head) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            return deleteDuplicates(head.next);
        }
        head.next = deleteDuplicates(head.next);
        return head;
    }


    /**
     * 84. Largest Rectangle in Histogram
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i <= heights.length; i++) {
            int side = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || heights[stack.peek()] <= side) {
                stack.push(i);
            } else {
                Integer pop = stack.pop();
                int wide = stack.isEmpty() ? i : i - stack.peek() - 1;
                result = Math.max(result, wide * heights[pop]);
                i--;
            }
        }
        return result;
    }

    /**
     * 88. Merge Sorted Array
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        m--;
        n--;
        k--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[k--] = nums1[m--];
            } else {
                nums1[k--] = nums2[n--];
            }
        }
        while (n >= 0) {
            nums1[k--] = nums2[n--];
        }
    }

    /**
     * 93. Restore IP Addresses
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String a = s.substring(0, i);
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k);

                    if (validIpSeq(a) && validIpSeq(b) && validIpSeq(c) && validIpSeq(d)) {
                        result.add(a + "." + b + "." + c + "." + d);
                    }
                }
            }
        }
        return result;
    }

    private boolean validIpSeq(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        int len = s.length();
        if (len > 3) {
            return false;
        }
        if (len > 1 && s.charAt(0) == '0') {
            return false;
        }
        int value = Integer.parseInt(s);

        return value >= 0 && value <= 255;
    }

}
