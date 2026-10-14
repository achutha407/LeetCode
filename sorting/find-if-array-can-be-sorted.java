import java.util.*;

class Solution {
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        
        // Check if the array is already sorted
        if (isSorted(nums)) return true;

        // Process array in chunks based on adjacent elements having the same set bit count
        for (int i = 0; i < n - 1; i++) {
            if (Integer.bitCount(nums[i]) == Integer.bitCount(nums[i + 1]) && nums[i] > nums[i + 1]) {
                swap(nums, i, i + 1);
                i = -1; // Restart checking from the beginning after each swap
            }
        }

        // Check if the final array is sorted
        return isSorted(nums);
    }

    private boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.canSortArray(new int[]{8, 4, 2, 30, 15})); // Expected: true ✅
        System.out.println(sol.canSortArray(new int[]{1, 2, 3, 4, 5})); // Expected: true ✅
        System.out.println(sol.canSortArray(new int[]{3, 16, 8, 4, 2})); // Expected: false ✅
    }
}

