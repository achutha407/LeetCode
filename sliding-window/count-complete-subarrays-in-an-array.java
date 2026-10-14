import java.util.*;

class Solution {
    public int countCompleteSubarrays(int[] nums) {
        // Step 1: Get total number of unique elements in nums
        Set<Integer> totalDistinct = new HashSet<>();
        for (int num : nums) {
            totalDistinct.add(num);
        }
        int totalUnique = totalDistinct.size();

        int count = 0;
        // Step 2: Brute-force all subarrays and count the ones that are complete
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                seen.add(nums[j]);
                if (seen.size() == totalUnique) {
                    count++;
                }
            }
        }
        return count;
    }
}
