import java.util.*;

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;

        int[] dp = new int[n];        // dp[i] = size of the largest subset ending at index i
        int[] prev = new int[n];      // prev[i] = index of previous element in subset
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxIdx = 0;               // index of the largest subset

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > dp[maxIdx]) {
                maxIdx = i;
            }
        }

        // Reconstruct the subset
        List<Integer> result = new ArrayList<>();
        while (maxIdx >= 0) {
            result.add(nums[maxIdx]);
            maxIdx = prev[maxIdx];
        }

        Collections.reverse(result);
        return result;
    }
}
