import java.util.Arrays;

class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = lowerBound(nums, i + 1, nums.length - 1, lower - nums[i]);
            int right = upperBound(nums, i + 1, nums.length - 1, upper - nums[i]);
            count += right - left + 1;
        }
        return count;
    }

    private int lowerBound(int[] nums, int left, int right, int target) {
        int res = right + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private int upperBound(int[] nums, int left, int right, int target) {
        int res = left - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}
