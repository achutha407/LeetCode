class Solution {
    public int countGoodNumbers(long n) {
        long mod = 1000000007;
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;
        long evenPow = pow(5, evenCount, mod);
        long oddPow = pow(4, oddCount, mod);
        return (int)((evenPow * oddPow) % mod);
    }

    private long pow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }
}
