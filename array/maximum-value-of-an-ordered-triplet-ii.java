class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0; // No valid triplet

        long maxValue = Long.MIN_VALUE;
        int best_i = nums[0];

        // Suffix max array for nums[k]
        int[] best_k = new int[n];
        best_k[n - 1] = nums[n - 1];

        for (int k = n - 2; k > 0; k--) {
            best_k[k] = Math.max(best_k[k + 1], nums[k]);
        }

        // Iterate over j
        for (int j = 1; j < n - 1; j++) {
            maxValue = Math.max(maxValue, (long) (best_i - nums[j]) * best_k[j + 1]);
            best_i = Math.max(best_i, nums[j]); // Update best_i
        }

        return Math.max(0, maxValue); // If all values are negative, return 0
    }
}
