package org.learn.algorithm.nowcode;

/**
 * @author luk
 */
public class SortSolution {

    public static void main(String[] args) {
        SortSolution solution = new SortSolution();
        int[] nums = new int[]{5, 2, 3, 1, 4};
        solution.heapSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    private void quickSortAlgorithm(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void mergeSortAlgorithm(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int partition = partition(nums, start, end);
        quickSort(nums, start, partition - 1);
        quickSort(nums, partition + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        while (start < end) {
            while (start < end && nums[end] >= pivot) {
                end--;
            }
            if (start < end) {
                nums[start] = nums[end];
                start++;
            }
            while (start < end && nums[start] <= pivot) {
                start++;
            }
            if (start < end) {
                nums[end] = nums[start];
                end--;
            }
        }
        nums[start] = pivot;
        return start;
    }


    private void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, mid, end);
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int[] result = new int[end - start + 1];
        int i = start;
        int k = mid + 1;
        int index = 0;
        while (i <= mid && k <= end) {
            if (nums[i] < nums[k]) {
                result[index++] = nums[i++];
            } else {
                result[index++] = nums[k++];
            }
        }
        while (i <= mid) {
            result[index++] = nums[i++];
        }
        while (k <= end) {
            result[index++] = nums[k++];
        }
        System.arraycopy(result, 0, nums, start, result.length);
    }

    private void heapSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeapSort(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            adjustHeapSort(nums, 0, i);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    private void adjustHeapSort(int[] nums, int i, int len) {
        int pivot = nums[i];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && nums[k] < nums[k + 1]) {
                k = k + 1;
            }
            if (nums[k] > pivot) {
                nums[i] = nums[k];
                i = k;
            } else {
                break;
            }
        }
        nums[i] = pivot;
    }

}
