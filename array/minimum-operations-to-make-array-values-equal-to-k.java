import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minOperations(int[] nums, int k) {
        Set<Integer> st = new HashSet<>();
        
        for (int x : nums) {  // Iterate over the nums array, not k
            if (x < k) {
                return -1;
            } else if (x > k) {
                st.add(x);
            }
        }
        
        return st.size();
    }
}
