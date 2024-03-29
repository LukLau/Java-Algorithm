package org.learn.algorithm.swordoffer;

import org.learn.algorithm.datastructure.*;

import java.util.*;

/**
 * @author luk
 * @date 2021/5/12
 */
public class SwordOffer {

    public static void main(String[] args) {
        SwordOffer offer = new SwordOffer();

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
//        node2.left = new TreeNode(4);
//        node2.right = new TreeNode(5);
        root.left = node2;

//        TreeNode node6 = new TreeNode(6);
//        node3.left = node6;
        root.right = node3;

        node3.left = new TreeNode(4);
        node3.right = new TreeNode(5);

//        offer.isCompleteTree(root);
//        offer.minCostClimbingStairs(new int[]{2, 5, 20});

        int[][] matrix = new int[][]{{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
//        offer.minPathSum(matrix);
        int[] money = new int[]{357, 322, 318, 181, 22, 158, 365};
        int minMoney = offer.minMoney(money, 4976);

        System.out.println(minMoney);

    }


    /**
     * NC10 大数乘法
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string字符串 第一个整数
     * @param t string字符串 第二个整数
     * @return string字符串
     */
    public String bigNumberMulti(String s, String t) {
        // write code here
        if (s == null || t == null) {
            return "0";
        }
        int m = s.length();
        int n = t.length();
        int[] pos = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int val = Character.getNumericValue(s.charAt(i)) * Character.getNumericValue(t.charAt(j)) + pos[i + j + 1];

                pos[i + j + 1] = val % 10;

                pos[i + j] += val / 10;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int num : pos) {
            if (!(num == 0 && builder.length() == 0)) {
                builder.append(num);
            }
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }


    /**
     * WC80 二维数组中的查找
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        if (array == null || array.length == 0) {
            return true;
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


    /**
     * NC71 旋转数组的最小数字
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] == array[right]) {
                right--;
            }
            int mid = left + (right - left) / 2;
            if (array[mid] <= array[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return array[left];
    }


    /**
     * WC109 栈的压入、弹出序列
     *
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(i);
            while (!stack.isEmpty() && popA[j] == pushA[stack.peek()]) {
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
        if (start > end) {
            return false;
        }
        if (start == end) {
            return true;
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
        if (leftIndex == start || leftIndex == end) {
            return true;
        }
        return intervalVerify(sequence, start, leftIndex - 1) && intervalVerify(sequence, leftIndex, end - 1);
    }

    /**
     * WC97 二叉树中和为某一值的路径
     *
     * @param root
     * @param target
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        internalPath(result, new ArrayList<>(), root, target);
        return result;
    }

    private void internalPath(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> tmp, TreeNode root, int target) {
        if (root == null) {
            return;
        }
        tmp.add(root.val);
        if (root.left == null && root.right == null && root.val == target) {
            result.add(new ArrayList<>(tmp));
        } else {
            if (root.left != null) {
                internalPath(result, tmp, root.left, target - root.val);
            }
            if (root.right != null) {
                internalPath(result, tmp, root.right, target - root.val);
            }
        }
        tmp.remove(tmp.size() - 1);
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode current = pHead;
        while (current != null) {

            RandomListNode next = current.next;

            RandomListNode node = new RandomListNode(current.label);

            node.next = next;

            current.next = node;

            current = next;
        }
        current = pHead;
        while (current != null) {
            RandomListNode next = current.next;
            if (current.random != null) {
                next.random = current.random.next;
            }
            current = next.next;
        }
        current = pHead;
        RandomListNode copyHead = current.next;
        while (current.next != null) {
            RandomListNode next = current.next;
            current.next = next.next;
            current = next;
        }
        return copyHead;
    }

    /**
     * NC64 二叉搜索树与双向链表
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = null;
        TreeNode p = pRootOfTree;
        TreeNode prev = null;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();

            if (prev != null) {
                prev.right = p;
                p.left = prev;

            }
            prev = p;
            if (head == null) {
                head = p;
            }
            p = p.right;
        }
        return head;

    }


    /**
     * todo
     * NC121 字符串的排列
     *
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }
        char[] words = str.toCharArray();
        Arrays.sort(words);
        boolean[] used = new boolean[words.length];
        return null;
    }

    private void swapWord(char[] words, int i, int j) {
        char tmp = words[i];
        words[i] = words[j];
        words[j] = tmp;
    }


    /**
     * BM59 N皇后问题
     *
     * @param n int整型 the n
     * @return int整型
     */
    public int Nqueen(int n) {
        // write code here
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n];

        return internalNqueen(dp, 0, n);

    }

    private int internalNqueen(int[] dp, int row, int n) {
        int count = 0;
        if (row == n) {
            count++;
            return count;
        }
        for (int j = 0; j < n; j++) {
            if (isValidQueen(dp, row, j)) {
                dp[row] = j;
                count += internalNqueen(dp, row + 1, n);
                dp[row] = -1;
            }
        }
        return count;
    }

    private boolean isValidQueen(int[] dp, int row, int column) {
        for (int i = row - 1; i >= 0; i--) {
            if (dp[i] == column || Math.abs(i - row) == Math.abs(dp[i] - column)) {
                return false;

            }
        }
        return true;
    }


    /**
     * BM60 括号生成
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n int整型
     * @return string字符串ArrayList
     */

    private void internalGenerate(ArrayList<String> result, String s, int open, int close, int n) {
        if (s.length() == 2 * n) {
            result.add(s);
            return;
        }
        if (open < n) {
            internalGenerate(result, s + "(", open + 1, close, n);
        }
        if (close < open) {
            internalGenerate(result, s + ")", open, close + 1, n);
        }

    }


    /**
     * BM61 矩阵最长递增路径
     * <p>
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 递增路径的最大长度
     *
     * @param matrix int整型二维数组 描述矩阵的每个数
     * @return int整型
     */
    public int maxIncrement(int[][] matrix) {
        // write code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[] dp = new int[matrix[0].length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < matrix[0].length; i++) {
            if (matrix[0][i] > matrix[0][i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) {
                    if (matrix[i][j] > matrix[i - 1][j]) {
                        dp[i] = dp[i - 1] + 1;
                    }
                }
            }

        }
        return 0;
    }

    public int maxIncremenII(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        boolean[][] used = new boolean[row][column];
        int[][] digit = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int val = Integer.MIN_VALUE;

                int current = matrix[i][j];

                int count = 1;


            }
        }
        return result;

    }

    private int increasementQueue(int min, int[][] matrix, int i, int j, boolean[][] used) {
        if (i < 0 || i == matrix.length || j < 0 || j == matrix[i].length || used[i][j]) {
            return 0;
        }
        int val = matrix[i][j];

        if (val < min) {
            return 0;
        }
        int count = 1;
        used[i][j] = true;
        count = Math.max(count, increasementQueue(val, matrix, i - 1, j, used));
        count += increasementQueue(val, matrix, i + 1, j, used);
        count += increasementQueue(val, matrix, i, j - 1, used);
        count += increasementQueue(val, matrix, i, j + 1, used);
        used[i][j] = false;
        return count;
    }


    /**
     * BM64 最小花费爬楼梯
     * <p>
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param cost int整型一维数组
     * @return int整型
     */
    public int minCostClimbingStairs(int[] cost) {
        // write code here
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length <= 2) {
            return cost.length == 1 ? cost[0] : Math.min(cost[0], cost[1]);
        }
        int prev = 0;
        int current = 0;
        for (int num : cost) {
            int tmp = current;
            current = Math.min(current, prev) + num;
            prev = tmp;
        }
        return Math.min(prev, current);

    }

    public int minCostClimbingStairsII(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int prev = 0;
        int current = 0;
        for (int val : cost) {
            int tmp = current;

            current = Math.min(current, prev) + val;

            prev = tmp;
        }
        return Math.min(prev, current);
    }


    /**
     * BM65 最长公共子序列(二)
     * longest common subsequence
     *
     * @param s1 string字符串 the string
     * @param s2 string字符串 the string
     * @return string字符串
     */
    public String LCS(String s1, String s2) {
        // write code here
        if (s1 == null || s2 == null) {
            return "-1";
        }
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        while (m >= 1 && n >= 1) {
            if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
                builder.append(s1.charAt(m - 1));
                m--;
                n--;
            } else if (dp[m - 1][n] > dp[m][n - 1]) {
                m--;
            } else {
                n--;
            }
        }
        return builder.length() == 0 ? "-1" : builder.reverse().toString();
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * longest common substring
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LongestCommonSubstring(String str1, String str2) {
        // write code here
        if (str1 == null || str2 == null) {
            return "-1";
        }
//        int m = str1.length();
//        int n = str2.length();
//        int[][] dp = new int[m + 1][n + 1];
//        int result = 0;
//        int endIndex = -1;
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                    if (dp[i][j] > result) {
//                        result = dp[i][j];
//                        endIndex = i;
//                    }
//                }
//            }
//        }
//        if (result == 0) {
//            return "-1";
//        }
//        return str1.substring(endIndex - result, endIndex);
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        int result = 0;
        int endIndex = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > result) {
                        result = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        if (result > 0) {
            return str1.substring(endIndex - result, endIndex);
        }
        return "-1";
    }


    /**
     * BM68 矩阵的最小路径和
     *
     * @param matrix int整型二维数组 the matrix
     * @return int整型
     */
    public int minPathSum(int[][] matrix) {
        // write code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int[][] dp = new int[row][column];
        for (int j = 0; j < column; j++) {
            dp[0][j] = j == 0 ? matrix[0][0] : dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
                }
            }
        }
        return dp[row - 1][column - 1];
    }

    /**
     * BM69 把数字翻译成字符串
     * <p>
     * 解码
     *
     * @param nums string字符串 数字串
     * @return int整型
     */
    public int convertStringToNum(String nums) {
        // write code here
        if (nums == null || nums.isEmpty()) {
            return 0;
        }
        int len = nums.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            String first = nums.substring(i - 1, i);
            int firstValue = Integer.parseInt(first);
            if (firstValue >= 1 && firstValue <= 9) {
                dp[i] = dp[i - 1];
            }
            String second = i < 2 ? "0" : nums.substring(i - 2, i);
            int secondValue = Integer.parseInt(second);
            if (secondValue >= 10 && secondValue <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }


    /**
     * BM60 括号生成
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str int整型
     * @return string字符串ArrayList
     */


    public ArrayList<String> PermutationV2(String str) {
        if (str == null) {
            return new ArrayList<>();
        }
        ArrayList<String> result = new ArrayList<>();
        char[] words = str.toCharArray();
        Arrays.sort(words);
        boolean[] used = new boolean[words.length];
        intervalPermutation(result, words, "", used);
        return result;

    }

    private void intervalPermutation(List<String> result, char[] words, String s, boolean[] used) {
        if (s.length() == used.length) {
            result.add(s);
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && words[i] == words[i - 1] && !used[i] && used[i - 1]) {
                continue;
            }
            used[i] = true;
            s += words[i];
            intervalPermutation(result, words, s, used);
            s = s.substring(0, s.length() - 1);
            used[i] = false;
        }
    }

    public int MoreThanHalfNum_Solution(int[] array) {
        int candidate = array[0];
        int count = 0;
        for (int num : array) {
            if (num == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = num;
                    count = 1;
                }
            }
        }
        count = 0;
        for (int num : array) {
            if (num == candidate) {
                count++;
            }
        }
        return 2 * count >= array.length ? candidate : -1;
    }

    /**
     * NC74 数字在升序数组中出现的次数
     *
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (array[left] != k) {
            return 0;
        }
        int firstIndex = left;
        right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (array[mid] > k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left - firstIndex + 1;
    }


    /**
     * 解码
     *
     * @param nums string字符串 数字串
     * @return int整型
     */
    public int decodeNumsWay(String nums) {
        // write code here
        if (nums == null || nums.length() == 0) {
            return 0;
        }
        int len = nums.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            String first = nums.substring(i - 1, i);
            int firstValue = Integer.parseInt(first);
            if (firstValue >= 1 && firstValue <= 9) {
                dp[i] = dp[i - 1];
            }
            String second = i >= 2 ? nums.substring(i - 2, i) : "0";
            int secondValue = Integer.parseInt(second);
            if (secondValue >= 10 && secondValue <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] FindNumsAppearOnce(int[] array) {
        // write code here
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        int flag = 0;
        for (int number : array) {

            flag ^= number;
        }
        flag &= -flag;
        int[] result = new int[2];
        for (int number : array) {
            if ((number & flag) != 0) {
                result[0] ^= number;
            } else {
                result[1] ^= number;
            }
        }
        Arrays.sort(result);
        return result;
    }


    public String LeftRotateString(String str, int n) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        int len = str.length();
        str += str;
        return str.substring(n, n + len);
    }

    /**
     * WC106 翻转单词序列
     *
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] words = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            builder.append(new StringBuilder(words[i]).reverse());
            if (i != words.length - 1) {
                builder.append(" ");
            }
        }
        return builder.reverse().toString();
    }


    /**
     * WC76 扑克牌顺子
     *
     * @param numbers
     * @return
     */
    public boolean IsContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        int flag = 0;
        int min = 14;
        int max = -1;
        for (int number : numbers) {
            if (number == 0) {
                continue;
            }
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
            if ((flag & (1 << number)) != 0) {
                return false;
            }
            if (max - min >= 5) {
                return false;
            }
            flag |= 1 << number;
        }
        return true;
    }

    /**
     * WC88 孩子们的游戏(圆圈中最后剩下的数)
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(i);
        }
        int currentIndex = 0;
        while (result.size() > 1) {
            int index = (currentIndex + m - 1) % result.size();
            result.remove(index);
            currentIndex = index % result.size();
        }
        return result.get(0);
    }

    /**
     * todo
     * NC125 和为K的连续子数组
     * max length of the subarray sum = k
     *
     * @param arr int整型一维数组 the array
     * @param k   int整型 target
     * @return int整型
     */
    public int maxlenEqualK(int[] arr, int k) {
        // write code here
        int local = 0;
        int result = 0;
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            local += arr[i];
            while (local == k) {
                result = Math.max(result, i - left + 1);
                local -= arr[left++];
            }
        }
        return result;
    }


    public int Sum_Solution(int n) {
        int sum = n;
        boolean valid = sum > 0 && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
    }


    /**
     * 最大乘积
     *
     * @param A int整型一维数组
     * @return long长整型
     */
    public long threeNumMaxMulti(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        // write code here
        long firstMax = Integer.MIN_VALUE;
        long secondMax = Integer.MIN_VALUE;
        long thirdMax = Integer.MIN_VALUE;

        long firstMin = Integer.MAX_VALUE;
        long secondMin = Integer.MAX_VALUE;

        for (int number : A) {
            if (number > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = number;
            } else if (number > secondMax) {
                thirdMax = secondMax;
                secondMax = number;
            } else if (number > thirdMax) {
                thirdMax = number;
            }
            if (number < firstMin) {
                secondMin = firstMin;
                firstMin = number;
            } else if (number < secondMin) {
                secondMin = number;
            }
        }
        if (firstMin > 0 || firstMax < 0) {
            return firstMax * secondMax * thirdMax;
        }
        return Math.max(firstMax * secondMax * thirdMax, firstMin * secondMin * firstMax);
    }


    /**
     * NC114 旋转字符串
     * 旋转字符串
     *
     * @param A string字符串
     * @param B string字符串
     * @return bool布尔型
     */
    public boolean rotateString(String A, String B) {
        if (A.equals(B)) {
            return true;
        }
        // write code here
        int m = A.length();
        int n = B.length();
        for (int i = 1; i < m; i++) {
            String s1 = A.substring(0, i);
            String s2 = A.substring(i, m);

            String s3 = s2 + s1;
            if (s3.equals(B)) {
                return true;
            }
        }
        return false;
    }

    /**
     * todo
     * NC115 栈和排序
     * 栈排序
     *
     * @param a int整型一维数组 描述入栈顺序
     * @return int整型一维数组
     */
    public int[] stackSort(int[] a) {
        // write code here
        if (a == null || a.length == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int number : a) {
            priorityQueue.offer(number);
        }
        int[] result = new int[a.length];
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            stack.push(i);
            while (!stack.isEmpty() && a[stack.peek()] == priorityQueue.peek()) {
                result[index++] = priorityQueue.poll();
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            result[index++] = a[stack.pop()];
        }
        return result;
    }


    /**
     * todo
     *
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int tmp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = tmp;
        }
        return num1;
    }

    public int StrToInt(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        int index = 0;
        int sign = 1;
        char[] words = str.toCharArray();
        if (words[0] == '-' || words[0] == '+') {
            sign = words[0] == '+' ? 1 : -1;
            index = 1;
        }
        long result = 0;
        while (index < words.length && Character.isDigit(words[index])) {
            result = result * 10 + Character.getNumericValue(words[index]);
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
            index++;
        }
        if (index != words.length) {
            return 0;
        }
        return (int) (result * sign);
    }


    /**
     * WC116 构建乘积数组
     *
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0) {
            return new int[]{};
        }
        int base = 1;
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = base;
            base *= A[i];
        }
        base = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            result[i] *= base;
            base *= A[i];
        }
        return result;
    }


    /**
     * WC149 正则表达式匹配
     * todo
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str     string字符串
     * @param pattern string字符串
     * @return bool布尔型
     */
    public boolean matchV2(String str, String pattern) {
        // write code here
        if (pattern.isEmpty()) {
            return str.isEmpty();
        }
        int m = str.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = pattern.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    if (str.charAt(i - 1) != pattern.charAt(j - 2) && pattern.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * WC150 表示数值的字符串
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str string字符串
     * @return bool布尔型
     */
    public boolean isNumeric(String str) {
        // write code here
        if (str == null) {
            return false;
        }
        str = str.trim();
        if (str.isEmpty()) {
            return false;
        }
        boolean seenNumber = false;
        boolean seenDigit = false;
        boolean seenNumberAfterE = false;
        boolean seenE = false;
        char[] words = str.toCharArray();
        for (int i = 0; i < words.length; i++) {
            char tmp = words[i];
            if (Character.isDigit(tmp)) {
                seenNumber = true;
                seenNumberAfterE = true;
            } else if (tmp == 'e' || tmp == 'E') {
                if (seenE || !seenNumber) {
                    return false;
                }
                if (!(Character.isDigit(words[i - 1]) || words[i - 1] == '.')) {
                    return false;
                }
                seenE = true;
                seenNumberAfterE = false;
            } else if (tmp == '-' || tmp == '+') {
                if (i > 0 && !(words[i - 1] == 'e' || words[i - 1] == 'E')) {
                    return false;
                }
            } else if (tmp == '.') {
                if (seenDigit || seenE) {
                    return false;
                }
                seenDigit = true;
            } else {
                return false;
            }
        }
        return seenNumber && seenNumberAfterE;
    }

    /**
     * NC3 链表中环的入口结点
     *
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = pHead;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        if (pHead.val == pHead.next.val) {
            ListNode current = pHead.next;
            while (current != null && current.val == pHead.val) {
                current = current.next;
            }
            return deleteDuplication(current);
        }
        pHead.next = deleteDuplication(pHead.next);
        return pHead;
    }

    /**
     * WC120 二叉树的下一个结点
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        TreeLinkNode right = pNode.right;
        if (right != null) {
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }
        while (pNode.next != null && pNode.next.right == pNode) {
            pNode = pNode.next;
        }

        return pNode.next;
    }

    /**
     * WC121 对称的二叉树
     *
     * @param pRoot
     * @return
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return false;
        }
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @return bool布尔型
     */
    public boolean isValidBST(TreeNode root) {
        // write code here
        if (root == null) {
            return false;
        }
        return validBST(Integer.MIN_VALUE, root, Integer.MAX_VALUE);
    }

    private boolean validBST(int minValue, TreeNode root, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val <= minValue || root.val >= maxValue) {
            return false;
        }
        return validBST(minValue, root.left, root.val) && validBST(root.val, root.right, maxValue);
    }

    /**
     * {1,2,3,5,#,7,8}
     * the tick: 无需 判断结点是否为null,再插入
     * {3,1,7,#,#,6,8}
     *
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        // write code here
        if (root == null) {
            return false;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(root);
        boolean isComplete = false;
        while (!linkedList.isEmpty()) {
            TreeNode poll = linkedList.poll();
            if (poll == null) {
                isComplete = true;
                continue;
            }
            if (isComplete) {
                return false;
            }
            linkedList.offer(poll.left);
            linkedList.offer(poll.right);
        }
        return true;
    }


    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        return exponent % 2 == 0 ? Power(base * base, exponent / 2) : base * Power(base * base, exponent / 2);
    }

    /**
     * WC151 链表中倒数最后k个结点
     *
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode pHead, int k) {
        if (k <= 0) {
            return null;
        }
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        int count = 1;
        ListNode fast = pHead;
        while (fast.next != null) {
            fast = fast.next;
            count++;
        }
        if (k > count) {
            return null;
        }
        k %= count;
        ListNode slow = pHead;
        if (k != 0) {
            for (int i = 0; i < count - k; i++) {
                slow = slow.next;
            }
        }
        return slow;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param matrix char字符型二维数组
     * @param word   string字符串
     * @return bool布尔型
     */
    public boolean hasPath(char[][] matrix, String word) {
        // write code here
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        boolean[][] used = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == word.charAt(0) && validPath(matrix, i, j, used, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validPath(char[][] matrix, int i, int j, boolean[][] used, int start, String word) {
        if (start == word.length()) {
            return true;
        }
        if (i < 0 || i == matrix.length || j < 0 || j == matrix[0].length || used[i][j] || matrix[i][j] != word.charAt(start)) {
            return false;
        }
        used[i][j] = true;
        if (validPath(matrix, i - 1, j, used, start + 1, word) || validPath(matrix, i + 1, j, used, start + 1, word) || validPath(matrix, i, j - 1, used, start + 1, word) || validPath(matrix, i, j + 1, used, start + 1, word)) {
            return true;
        }
        used[i][j] = false;
        return false;
    }

    /**
     * WC153 在旋转过的有序数组中寻找目标值
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums   int整型一维数组
     * @param target int整型
     * @return int整型
     */
    public int search(int[] nums, int target) {
        // write code here
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * WC128 机器人的运动范围
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] used = new boolean[rows][cols];
        return count(used, 0, 0, rows, cols, threshold);
    }

    private int count(boolean[][] used, int i, int j, int rows, int cols, int threshold) {
        int result = 0;
        if (i < 0 || i >= rows || j < 0 || j >= cols || used[i][j]) {
            return 0;
        }
        int step = countCell(i) + countCell(j);
        if (step > threshold) {
            return 0;
        }
        used[i][j] = true;
        result++;
        result += count(used, i - 1, j, rows, cols, threshold);
        result += count(used, i + 1, j, rows, cols, threshold);
        result += count(used, i, j - 1, rows, cols, threshold);
        result += count(used, i, j + 1, rows, cols, threshold);
        return result;
    }

    private int countCell(int point) {
        int val = 0;
        while (point != 0) {
            val += point % 10;
            point /= 10;
        }
        return val;
    }


    /**
     * WC131 剪绳子
     *
     * @param target
     * @return
     */
    public int cutRope(int target) {
        if (target <= 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        if (target == 4) {
            return 4;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for (int i = 5; i <= target; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[target];
    }


    /**
     * WC112 树的子结构
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        return isSubTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    private boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
    }


    /**
     * WC114 和为S的两个数字
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int val = array[left] + array[right];
            if (val < sum) {
                left++;
            } else if (val > sum) {
                right--;
            } else {
                result.add(array[left]);
                result.add(array[right]);
            }
        }
        return result;
    }


    /**
     * WC115 丑数
     *
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if (index < 7) {
            return index;
        }
        int[] dp = new int[index];
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        dp[0] = 1;
        int i = 1;
        while (i < index) {
            int val = Math.min(Math.min(dp[index2] * 2, dp[index3] * 3), dp[index5] * 5);
            if (val == dp[index2] * 2) {
                index2++;
            }
            if (val == dp[index3] * 3) {
                index3++;
            }
            if (val == dp[index5] * 5) {
                index5++;
            }
            dp[i] = val;
            i++;
        }
        return dp[index - 1];
    }


    /**
     * todo
     * NC87 丢棋子问题
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回最差情况下扔棋子的最小次数
     *
     * @param n int整型 楼层数
     * @param k int整型 棋子数
     * @return int整型
     */
    public int chessSolution(int n, int k) {
        // write code here
        if (n <= 0 || k <= 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                int min = Integer.MAX_VALUE;
                for (int m = 1; m <= i; m++) {
                    min = Math.min(min, Math.max(dp[m - 1][j - 1], dp[i - m][j]));
                }
                dp[i][j] = 1 + min;
            }
        }
        return dp[n][k];
    }

    public int chessSolutionII(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                int tmp = Integer.MAX_VALUE;
                for (int m = 1; m <= i; m++) {
                    tmp = Math.min(tmp, Math.max(dp[m - 1][j - 1], dp[i - m][j]));
                }
            }
        }
        return dp[n][k];

    }

    /**
     * NC95 数组中的最长连续子序列
     * max increasing subsequence
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int MLS(int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (!map.containsKey(num)) {
                Integer leftSide = map.getOrDefault(num - 1, 0);
                Integer rightSide = map.getOrDefault(num + 1, 0);
                int tmp = leftSide + rightSide + 1;
                result = Math.max(result, tmp);
                map.put(num, tmp);
                map.put(num - leftSide, tmp);
                map.put(num + rightSide, tmp);
            }
        }
        return result;
    }

    /**
     * NC132 环形链表的约瑟夫问题
     *
     * @param n int整型
     * @param m int整型
     * @return int整型
     */
    public int ysf(int n, int m) {
        // write code here
        if (m < 1 || n < 1) return -1;
        int last = 0;
        // i代表有目前有个人
        //最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last + 1;
    }

    /**
     * 最大数
     *
     * @param nums int整型一维数组
     * @return string字符串
     */
    public String bigNumber(int[] nums) {
        // write code here
        String[] params = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            params[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(params, (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s2.compareTo(s1);
        });
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            if (i == 0 && "0".equals(param)) {
                return "0";
            }
            builder.append(param);
        }
        return builder.toString();
    }


    /**
     * todo
     * NC126 换钱的最少货币数
     * 最少货币数
     *
     * @param arr int整型一维数组 the array
     * @param aim int整型 the target
     * @return int整型
     */
    public int minMoney(int[] arr, int aim) {
        // write code here
        if (arr == null || arr.length == 0) {
            return -1;
        }
//        int[][] dp = new int[arr.length][aim + 1];
//        for (int j = 1; j <= aim; j++) {
//            dp[0][j] = Integer.MAX_VALUE;
//            if (j - arr[0] >= 0 && dp[0][j - arr[0]] != Integer.MAX_VALUE) {
//                dp[0][j] = 1 + dp[0][j - arr[0]];
//            }
//        }
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = arr[0]; j <= aim; j++) {
//                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != Integer.MAX_VALUE) {
//                    // 判断不使用当前种类的货币和仅使用一张当前种类的货币这两种情况下，哪一种方案使用的货币少
//                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - arr[i]] + 1);
//                } else {
//                    // 不使用当前种类的货币
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }
//        return dp[arr.length - 1][aim] == Integer.MAX_VALUE ? -1 : dp[arr.length - 1][aim];

        int[][] dp = new int[aim + 1][arr.length];
        for (int i = 1; i <= aim; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            if (i - arr[0] >= 0 && dp[i - arr[0]][0] != Integer.MAX_VALUE) {
                dp[i][0] = 1 + dp[i - arr[0]][0];
            }
        }
        for (int i = 1; i <= aim; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (i - arr[j] >= 0 && dp[i - arr[j]][j] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - arr[j]][j] + 1);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[aim][arr.length - 1] == Integer.MAX_VALUE ? -1 : dp[aim][arr.length - 1];
    }

    public int minMoneyIII(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= aim; i++) {
            int min = Integer.MAX_VALUE;
            for (int money : arr) {
                if (i - money >= 0 && dp[i - money] != Integer.MAX_VALUE) {
                    min = Math.min(min, dp[i - money] + 1);
                }
            }
            dp[i] = min;
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

    public int minMoneyII(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= aim; i++) {
            int min = Integer.MAX_VALUE;
            for (int money : arr) {
                if (i - money >= 0 && dp[i - money] != Integer.MAX_VALUE) {
                    min = Math.min(min, 1 + dp[i - money]);
                }
            }
            dp[i] = min;
        }
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }


    /**
     * 寻找最后的山峰
     *
     * @param a int整型一维数组
     * @return int整型
     */
    public int findLatestPeek(int[] a) {
        // write code here
        if (a == null || a.length == 0) {
            return -1;
        }
        for (int i = a.length - 1; i >= 1; i--) {
            if (a[i] > a[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * NC28 最小覆盖子串
     *
     * @param S string字符串
     * @param T string字符串
     * @return string字符串
     */
    public String minWindow(String S, String T) {
        // write code here
        if (S == null || T == null) {
            return "";
        }
        int[] hash = new int[512];
        int n = T.length();
        for (int i = 0; i < n; i++) {
            hash[T.charAt(i)]++;
        }
        int result = Integer.MAX_VALUE;

        int head = 0;
        int begin = 0;

        int endIndex = 0;

        int m = S.length();

        while (endIndex < m) {
            if (hash[S.charAt(endIndex++)]-- > 0) {
                n--;
            }
            while (n == 0) {
                if (endIndex - begin < result) {
                    head = begin;
                    result = endIndex - begin;
                }
                if (hash[S.charAt(begin++)]++ == 0) {
                    n++;
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            return "";
        }
        return S.substring(head, head + result);
    }


    /**
     * 最大正方形
     *
     * @param matrix char字符型二维数组
     * @return int整型
     */
    public int maxSquare(char[][] matrix) {
        // write code here
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int result = 0;
        int[][] dp = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0) {
                    dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
                } else if (j == 0) {
                    dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
                } else if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                }
                result = Math.max(result, dp[i][j] * dp[i][j]);
            }
        }
        return result;
    }


    /**
     * NC110 旋转数组
     * 旋转数组
     *
     * @param n int整型 数组长度
     * @param m int整型 右移距离
     * @param a int整型一维数组 给定数组
     * @return int整型一维数组
     */
    public int[] partitionTwoString(int n, int m, int[] a) {
        // write code here
        swapArray(a, n - m, a.length - 1);
        swapArray(a, 0, n - m);
        swapArray(a, 0, a.length - 1);
        return null;
    }

    private void swapArray(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        for (int i = start; i <= (end + start) / 2; i++) {
            swap(nums, i, start + end - i);
        }
    }

    /**
     * BM71 最长上升子序列(一)
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 给定数组的最长严格上升子序列的长度。
     *
     * @param arr int整型一维数组 给定的数组
     * @return int整型
     */
    public int LIS(int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int result = 1;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
                if (dp[i] > result) {
                    result = dp[i];
                }
            }
        }
        return result;
    }


    /**
     * BM72 连续子数组的最大和
     *
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int local = Integer.MIN_VALUE;
        int global = Integer.MIN_VALUE;
        for (int num : array) {
            local = local >= 0 ? local + num : num;
            global = Math.max(local, global);
        }
        return global;
    }


    /**
     * BM73 最长回文子串
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param A string字符串
     * @return int整型
     */
    public int getLongestPalindrome(String A) {
        // write code here
        if (A == null || A.length() == 0) {
            return 0;
        }
        int m = A.length();
        boolean[][] dp = new boolean[m][m];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if (A.charAt(j) == A.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > result) {
                        result = i - j + 1;

                    }
                }
            }
        }
        return result;
    }

    /**
     * BM74 数字字符串转化成IP地址
     *
     * @param s string字符串
     * @return string字符串ArrayList
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        // write code here

        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<String> result = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < j + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < k + 4 && k < len; k++) {
                    String a = s.substring(0, i);
                    String b = s.substring(i, j);
                    String c = s.substring(j, k);
                    String d = s.substring(k);

                    if (validIP(a) && validIP(b) && validIP(c) && validIP(d)) {
                        result.add(a + "." + b + "." + c + "." + d);
                    }
                }
            }
        }
        return result;
    }

    private boolean validIP(String word) {
        if (word.isEmpty()) {
            return false;
        }
        int num = Integer.parseInt(word);
        if (num < 0 || num > 255) {
            return false;
        }
        return word.length() <= 1 || word.charAt(0) != '0';
    }


    /**
     * todo
     * BM75 编辑距离(一)
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str1 string字符串
     * @param str2 string字符串
     * @return int整型
     */
    public int editDistance(String str1, String str2) {
        // write code here
        if (str1 == null || str2 == null) {
            return 0;
        }
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[m][n];
    }


    private void swap(int[] nums, int i, int j) {
        int val = nums[i];
        nums[i] = nums[j];
        nums[j] = val;
    }


    /**
     * BM76 正则表达式匹配
     * <p>
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param str     string字符串
     * @param pattern string字符串
     * @return bool布尔型
     */
    public boolean match(String str, String pattern) {
        if (pattern.isEmpty()) {
            return str.isEmpty();
        }
        int m = str.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = pattern.charAt(j - 1) == '*' && dp[0][j - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    if (str.charAt(i - 1) != pattern.charAt(j - 2) && pattern.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
                }
            }
        }
        return dp[m][n];
    }


    /**
     * BM77 最长的括号子串
     *
     * @param s string字符串
     * @return int整型
     */
    public int longestValidParentheses(String s) {
        // write code here
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        int result = 0;
        int left = -1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
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


    /**
     * BM78 打家劫舍(一)
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int rob(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prev = 0;
        int current = 0;
        for (int value : nums) {
            int tmp = current;
            current = Math.max(current, prev + value);
            prev = tmp;
        }
        return Math.max(prev, current);
    }


    /**
     * todo
     * BM79 打家劫舍(二)
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int robII(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return Math.max(internalRob(nums, 0, nums.length - 1), internalRob(nums, 1, nums.length - 2));
    }

    private int internalRob(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prev = 0;
        int current = 0;
        for (int i = start; i <= end; i = i + 1) {
            int value = nums[i];
            int tmp = current;
            current = Math.max(current, prev + value);
            prev = tmp;
        }
        return Math.max(prev, current);
    }


    /**
     * BM80 买卖股票的最好时机(一)
     *
     * @param prices int整型一维数组
     * @return int整型
     */
    public int maxProfitII(int[] prices) {
        // write code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int cost = prices[0];
        int result = 0;
        for (int price : prices) {
            if (price > cost) {
                result += price - cost;
            }
            cost = price;
        }
        return result;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 两次交易所能获得的最大收益
     *
     * @param prices int整型一维数组 股票每一天的价格
     * @return int整型
     */
    public int maxProfitIII(int[] prices) {
        // write code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] left = new int[len];
        int leftCost = prices[0];
        int leftMaxValue = 0;
        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (price > leftCost) {
                leftMaxValue = Math.max(leftMaxValue, price - leftCost);
            } else {
                leftCost = price;
            }
            left[i] = leftMaxValue;
        }
        int[] right = new int[len + 1];
        int rightCost = prices[len - 1];
        int rightMaxValue = 0;
        for (int i = len - 2; i >= 0; i--) {
            if (prices[i] < rightCost) {
                rightMaxValue = Math.max(rightMaxValue, rightCost - prices[i]);
            } else {
                rightCost = prices[i];
            }
            right[i] = rightMaxValue;
        }
        int result = 0;
        for (int i = 1; i < len; i++) {
            result = Math.max(result, left[i] + right[i + 1]);
        }
        return result;
    }


    /**
     * NC117 合并二叉树
     *
     * @param t1 TreeNode类
     * @param t2 TreeNode类
     * @return TreeNode类
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // write code here
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        int val = t1.val + t2.val;
        t1.val = val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }


    /**
     * BM83 字符串变形
     *
     * @param s
     * @param n
     * @return
     */
    public String trans(String s, int n) {
        // write code here
        if (s == null || s.isEmpty()) {
            return "";
        }
        String[] words = s.split(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            String tmp = words[i];
            char[] charArray = tmp.toCharArray();
            for (char c : charArray) {
            }
        }
        return "";
    }


    /**
     * BM86 大数加法
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     *
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String bigNumCalculate(String s, String t) {
        // write code here
        if (s == null || t == null) {
            return "";
        }
        int m = s.length() - 1;
        int n = t.length() - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (m >= 0 || n >= 0 || carry > 0) {
            int val = (m >= 0 ? Character.getNumericValue(s.charAt(m--)) : 0) + (n >= 0 ? Character.getNumericValue(t.charAt(n--)) : 0) + carry;

            int tmp = val % 10;

            builder.append(tmp);

            carry = val / 10;

        }
        return builder.reverse().toString();
    }


    /**
     * BM87 合并两个有序的数组
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int A[], int m, int B[], int n) {
        int k = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (A[m] > B[n]) {
                A[k--] = B[n--];
            } else {
                A[k--] = A[m--];
            }
        }
        while (n >= 0) {
            A[k--] = B[n--];
        }

    }


    /**
     * BM89 合并区间
     *
     * @param intervals
     * @return
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        LinkedList<Interval> linkedList = new LinkedList<>();
        int index = 0;
        int size = intervals.size();
        while (index < size) {
            Interval current = intervals.get(index);
            if (!linkedList.isEmpty() && linkedList.peekLast().end >= current.start) {
                Interval prev = linkedList.peekLast();
                prev.end = Math.max(prev.end, current.end);
            } else {
                linkedList.offer(current);
            }
            index++;
        }
        return new ArrayList<>(linkedList);
    }

    /**
     * BM90 最小覆盖子串
     *
     * @param S string字符串
     * @param T string字符串
     * @return string字符串
     */
    public String minWindowii(String S, String T) {
        // write code here
        if (S == null || T == null) {
            return "";
        }
        int m = S.length();
        int n = T.length();
        int[] hash = new int[512];
        for (int i = 0; i < n; i++) {
            hash[T.charAt(i)]++;
        }
        int result = Integer.MAX_VALUE;
        int end = 0;
        int begin = 0;
        int head = 0;
        while (end < m) {
            if (hash[S.charAt(end++)]-- > 0) {
                n--;
            }
            while (n == 0) {
                if (end - begin < result) {
                    result = end - begin;
                    head = begin;
                }
                if (hash[S.charAt(begin++)]++ == 0) {
                    n++;
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            return "";
        }
        return S.substring(head, head + result);
    }


    /**
     * BM92 最长无重复子数组
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int maxLength(int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                left = Math.max(left, map.get(arr[i]) + 1);
            }
            map.put(arr[i], i);
            result = Math.max(result, i - left + 1);
        }
        return result;
    }

    public int maxLengthii(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] hash = new int[arr.length];
        int result = 0;
        int left = 0;
        for (int i = 0; i < arr.length; i++) {
            result = Math.max(result, i - left + 1);

            left = Math.max(i, hash[arr[i]]);

            hash[arr[i]] = i + 1;
        }
        return result;
    }

    /**
     * BM93 盛水最多的容器
     * <p>
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param height int整型一维数组
     * @return int整型
     */
    public int maxArea(int[] height) {
        // write code here
        if (height == null || height.length == 0) {
            return 0;
        }
        int result = 0;
        int leftSide = 0;
        int rightSide = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftSide) {
                    leftSide = height[left];
                } else {
                    result += leftSide - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightSide) {
                    rightSide = height[right];
                } else {
                    result += rightSide - height[right];
                }
                right--;
            }
        }
        return result;
    }


    /**
     * BM95 分糖果问题
     * pick candy
     *
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public int candy(int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1] && dp[i] < dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
            }

        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1] && dp[i] < dp[i + 1] + 1) {
                dp[i] = dp[i + 1] + 1;
            }

        }
        int result = 0;
        for (int tmp : dp) {
            result += tmp;
        }
        return result;
    }


    /**
     * todo
     * BM96 主持人调度（二）
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 计算成功举办活动需要多少名主持人
     *
     * @param n        int整型 有n个活动
     * @param startEnd int整型二维数组 startEnd[i][0]用于表示第i个活动的开始时间，startEnd[i][1]表示第i个活动的结束时间
     * @return int整型
     */
    public int minmumNumberOfHostii(int n, int[][] startEnd) {
        // write code here
        if (startEnd == null || startEnd.length == 0) {
            return 0;
        }
        Arrays.sort(startEnd, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int[] item : startEnd) {
            if (!priorityQueue.isEmpty() && priorityQueue.peek()[1] <= item[0]) {
                priorityQueue.poll();
            }
            priorityQueue.offer(item);
        }
        return priorityQueue.size();
    }


    /**
     * NC106 三个数的最大乘积
     * 最大乘积
     *
     * @param A int整型一维数组
     * @return long长整型
     */
    public long threeMultiMaxValue(int[] A) {
        // write code here
        if (A == null || A.length < 3) {
            return 0;
        }
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : A) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        return Math.max(max3 * max2 * max1, min1 * min2 * max1);
    }

    /**
     * NC114 旋转字符串
     * 旋转字符串
     *
     * @param A string字符串
     * @param B string字符串
     * @return bool布尔型
     */
    public boolean partitionTwoString(String A, String B) {
        // write code here
        if (A == null || B == null) {
            return false;
        }
        if (A.equals(B)) {
            return true;
        }
        if (A.length() != B.length()) {
            return false;
        }
        int n = A.length();
        for (int i = 1; i < n; i++) {
            String pre = A.substring(0, i);
            String next = A.substring(i);
            if (B.contains(pre) && B.contains(next)) {
                return true;
            }
        }
        return false;
    }

    /**
     * todo
     * 栈排序
     *
     * @param a int整型一维数组 描述入栈顺序
     * @return int整型一维数组
     */
    public int[] maxPopSequence(int[] a) {
        // write code here
        if (a == null || a.length == 0) {
            return new int[]{};
        }
        int[] list = new int[a.length];
        int max = Integer.MIN_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            max = Math.max(max, a[i]);
            list[i] = max;
        }
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[a.length];
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() > list[i + 1]) {
                result[index++] = stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }
        return result;
    }


    /**
     * NC85 拼接所有的字符串产生字典序最小的字符串
     *
     * @param strs string字符串一维数组 the strings
     * @return string字符串
     */
    public String minString(String[] strs) {
        // write code here
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s1.compareTo(s2);
        });
        StringBuilder builder = new StringBuilder();
        for (String item : strs) {
            builder.append(item);
        }
        return builder.toString();
    }


    /**
     * NC147 主持人调度
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算成功举办活动需要多少名主持人
     *
     * @param n        int整型 有n个活动
     * @param startEnd int整型二维数组 startEnd[i][0]用于表示第i个活动的开始时间，startEnd[i][1]表示第i个活动的结束时间
     * @return int整型
     */
    public int minmumNumberOfHost(int n, int[][] startEnd) {
        // write code here
        if (startEnd == null || startEnd.length == 0) {
            return 0;
        }
        Arrays.sort(startEnd, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int[] item : startEnd) {
            if (!priorityQueue.isEmpty() && priorityQueue.peek()[1] <= item[0]) {
                priorityQueue.poll();
            }
            priorityQueue.offer(item);
        }
        return priorityQueue.size();
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 最少需要跳跃几次能跳到末尾
     *
     * @param n int整型 数组A的长度
     * @param A int整型一维数组 数组A
     * @return int整型
     */
    public int Jump(int n, int[] A) {
        // write code here
        if (A == null || A.length == 0) {
            return 0;
        }
        int step = 0;
        int current = 0;
        int furthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            furthest = Math.max(i + A[i], furthest);
            if (i == current) {
                step++;
                current = furthest;
            }
        }
        return step;
    }


    /**
     * todo
     * NC150 二叉树的个数
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 计算二叉树个数
     *
     * @param n int整型 二叉树结点个数
     * @return int整型
     */
    public int numberOfTree(int n) {
        // write code here
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
                dp[i] %= 1000000007;
            }
        }
        return dp[n];
    }

    /**
     * todo
     * NC152 数的划分
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param n int 被划分的数
     * @param k int 化成k份
     * @return int
     */
    public int divideNumber(int n, int k) {
        // write code here
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
            }
        }
        return dp[n][k];
    }

    /**
     * todo
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param letters int二维数组
     * @return int
     */
    public int maxLetters(int[][] letters) {
        // write code here
        if (letters == null || letters.length == 0) {
            return 0;
        }
        int row = letters.length;
        int column = letters[0].length;
        return -1;
    }

    /**
     * todo
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param s string 一个字符串由小写字母构成，长度小于5000
     * @return int
     */
    public int longestPalindromeSubSeq(String s) {
        // write code here
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                }
            }
        }
        return -1;
    }

    /**
     * todo
     * NC157 单调栈
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int一维数组
     * @return int二维数组
     */
    public int[][] foundMonotoneStack(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) {
            return new int[][]{};
        }
        int[][] result = new int[nums.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
        }
        return null;
    }

    /**
     * todo
     * NC155 最长严格上升子数组(二)
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int maxSubArrayLengthTwo(int[] nums) {
        // write code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int result = 0;
        for (int i = 1; i < nums.length; i++) {

            dp[i] = Math.max(dp[i], 1 + dp[i - 1]);

            result = Math.max(result, dp[i]);
        }
        return dp[nums.length - 1];
    }


    /**
     * todo
     * NC137 表达式求值
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回表达式的值
     *
     * @param s string字符串 待计算的表达式
     * @return int整型
     */
    public int express(String s) {
        // write code here
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int m = s.length();
        int index = 0;
        char[] words = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        while (index < m) {
            if (words[index] == '(') {
                int endIndex = index;
                int count = 0;
                while (endIndex < m) {
                    if (words[endIndex] != '(' && words[endIndex] != ')') {
                        endIndex++;
                        continue;
                    }
                    if (words[endIndex] == '(') {
                        count++;
                    }
                    if (words[endIndex] == ')') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    }
                    endIndex++;
                }
                int val = express(s.substring(index + 1, endIndex));
                index = endIndex + 1;
                stack.push(val);
            }
            if (index < m && Character.isDigit(words[index])) {
                int tmp = 0;
                while (index < m && Character.isDigit(words[index])) {
                    tmp = tmp * 10 + Character.getNumericValue(words[index]);
                    index++;
                }
                stack.push(tmp);
            }
            if (index == m || words[index] != ' ') {
                if (sign == '-') {
                    stack.push(-1 * stack.pop());
                } else if (sign == '*') {
                    stack.push(stack.pop() * stack.pop());
                } else if (sign == '/') {
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                }
                if (index != m) {
                    sign = words[index];
                }
            }
            index++;
        }
        int result = 0;
        for (Integer item : stack) {
            result += item;
        }
        return result;
    }


    /**
     * NC21 链表内指定区间反转
     *
     * @param head ListNode类
     * @param m    int整型
     * @param n    int整型
     * @return ListNode类
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write code here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode slow = root;
        for (int i = 0; i < m - 1; i++) {
            slow = slow.next;
        }
        ListNode fast = root;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode start = slow.next;

        ListNode end = fast.next;
        for (int i = 0; i <= n - m; i++) {
            ListNode tmp = start.next;
            start.next = end;
            end = start;
            start = tmp;
        }
        slow.next = fast;
        return root.next;
    }


    /**
     * NC7 买卖股票的最好时机(一)
     *
     * @param prices int整型一维数组
     * @return int整型
     */
    public int maxProfit(int[] prices) {
        // write code here
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int cost = -prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > cost) {
                profit = Math.max(profit, prices[i] - cost);
            } else {
                cost = -prices[i];
            }
        }
        return profit;
    }


}
