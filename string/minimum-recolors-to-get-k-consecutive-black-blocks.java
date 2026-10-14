class Solution {
    public int minimumRecolors(String blocks, int k) {
        int minOps = Integer.MAX_VALUE, whiteCount = 0;
        for (int i = 0; i < k; i++) 
            if (blocks.charAt(i) == 'W') whiteCount++;
        minOps = whiteCount;
        for (int i = k; i < blocks.length(); i++) {
            if (blocks.charAt(i - k) == 'W') whiteCount--;
            if (blocks.charAt(i) == 'W') whiteCount++;
            minOps = Math.min(minOps, whiteCount);
        }
        return minOps;
    }
}
