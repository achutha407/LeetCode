public class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            long skip = dp[i + 1];
            long solve = questions[i][0] + (i + questions[i][1] + 1 < n ? dp[i + questions[i][1] + 1] : 0);
            dp[i] = Math.max(skip, solve);
        }

        return dp[0];
    }
}
