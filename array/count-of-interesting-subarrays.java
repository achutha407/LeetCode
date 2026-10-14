class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Long> countMap = new HashMap<>();
        countMap.put(0, 1L);
        int prefix = 0;
        long res = 0;
        for (int num : nums) {
            if (num % modulo == k) {
                prefix = (prefix + 1) % modulo;
            } else {
                prefix = prefix % modulo;
            }
            int target = (prefix - k + modulo) % modulo;
            res += countMap.getOrDefault(target, 0L);
            countMap.put(prefix, countMap.getOrDefault(prefix, 0L) + 1);
        }
        return res;
    }
}
