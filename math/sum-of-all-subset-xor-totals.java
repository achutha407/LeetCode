class Solution {
    public int subsetXORSum(int[] nums) {
        return helper(nums, 0, 0);
    }

    private int helper(int[] nums, int index, int currentXOR) {
        // Base case: if we reach the end of the array
        if (index == nums.length) {
            return currentXOR;
        }

        // Include current element in XOR
        int withCurrent = helper(nums, index + 1, currentXOR ^ nums[index]);

        // Exclude current element from XOR
        int withoutCurrent = helper(nums, index + 1, currentXOR);

        return withCurrent + withoutCurrent;
    }
}
