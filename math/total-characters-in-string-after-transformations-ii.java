class Solution {
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        final int MOD = 1_000_000_007;

        long[][] transition = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int len = nums.get(i);
            for (int j = 1; j <= len; j++) {
                transition[i][(i + j) % 26]++;
            }
        }

        long[][] result = new long[26][26];
        for (int i = 0; i < 26; i++) result[i][i] = 1;

        while (t > 0) {
            if ((t & 1) == 1) {
                result = multiply(result, transition, MOD);
            }
            transition = multiply(transition, transition, MOD);
            t >>= 1;
        }

        long total = 0;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            for (int i = 0; i < 26; i++) {
                total = (total + result[idx][i]) % MOD;
            }
        }

        return (int) total;
    }

    private long[][] multiply(long[][] a, long[][] b, int mod) {
        long[][] res = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < 26; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < 26; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return res;
    }
}
