class Solution {
    static final int MOD = 1_000_000_007;
    public int idealArrays(int n, int maxValue) {
        int[][] dp = new int[15][maxValue + 1];
        int[] primes = new int[maxValue + 1];
        for (int i = 1; i <= maxValue; i++) {
            dp[1][i] = 1;
        }

        for (int len = 2; len < 15; len++) {
            for (int i = 1; i <= maxValue; i++) {
                for (int j = 2 * i; j <= maxValue; j += i) {
                    dp[len][j] = (dp[len][j] + dp[len - 1][i]) % MOD;
                }
            }
        }

        long[] fact = new long[n + 1];
        long[] inv = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;
        inv[n] = pow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) inv[i] = inv[i + 1] * (i + 1) % MOD;

        long res = 0;
        for (int len = 1; len < 15; len++) {
            long count = 0;
            for (int i = 1; i <= maxValue; i++) {
                count = (count + dp[len][i]) % MOD;
            }
            if (n - 1 < len - 1) break;
            long ways = fact[n - 1] * inv[len - 1] % MOD * inv[n - len] % MOD;
            res = (res + count * ways % MOD) % MOD;
        }
        return (int) res;
    }

    long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}
