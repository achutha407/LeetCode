class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        int res = 0;
        for (int[] d : dominoes) {
            int val = Math.min(d[0], d[1]) * 10 + Math.max(d[0], d[1]);
            res += count[val];
            count[val]++;
        }
        return res;
    }
}

