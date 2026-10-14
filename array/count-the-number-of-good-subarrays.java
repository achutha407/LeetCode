class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        long count = 0, res = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            int val = nums[right];
            count += freq.getOrDefault(val, 0);
            freq.put(val, freq.getOrDefault(val, 0) + 1);
            while (count >= k) {
                res += nums.length - right;
                int leftVal = nums[left];
                freq.put(leftVal, freq.get(leftVal) - 1);
                count -= freq.get(leftVal);
                left++;
            }
        }
        return res;
    }
}
