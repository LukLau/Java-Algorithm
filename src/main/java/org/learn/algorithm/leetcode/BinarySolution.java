package org.learn.algorithm.leetcode;

import javax.print.attribute.UnmodifiableSetException;
import java.awt.font.NumericShaper;

/**
 * @author luk
 * @date 2021/4/8
 */
public class BinarySolution {

    // --二分查找系列//


    // 旋转数组查找系列//

    public static void main(String[] args) {
        BinarySolution solution = new BinarySolution();
        solution.findPeakElement(new int[]{2});
    }

    /**
     * 33. Search in Rotated Sorted Array
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
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
     * todo
     * 81. Search in Rotated Sorted Array II
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[right]) {
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (target < nums[mid] && nums[left] <= target) {
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
        return nums[left] == target;
    }

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int[] result = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) {
            return result;
        }
        result[0] = left;
        right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        result[1] = left;
        return result;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 153. Find Minimum in Rotated Sorted Array
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }

    /**
     * 154. Find Minimum in Rotated Sorted Array II
     *
     * @param nums
     * @return
     */
    public int findMinII(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            if (nums[start] == nums[end]) {
                end--;
                continue;
            }
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }

    public int findMinIIV2(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }


    // --OlogN 复杂度//

    // --矩阵查询-//

    /**
     * 162. Find Peak Element
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }


    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] == array[right]) {
                left++;
            }
            int mid = left + (right - left) / 2;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return array[left];

    }

    /**
     * 74. Search a 2D Matrix
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int i = row - 1;
        int j = 0;
        while (i >= 0 && j < column) {
            int val = matrix[i][j];
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
     * 240. Search a 2D Matrix II
     * todo
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        int i = row - 1;
        int j = 0;
        while (i >= 0 && j < column) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return true;
    }

}
