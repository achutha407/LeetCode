import java.util.Arrays;

class Solution {
    public int minCapability(int[] nums, int k) {
        int l = Arrays.stream(nums).min().getAsInt();
        int r = Arrays.stream(nums).max().getAsInt();

        while (l < r) {
            int m = (l + r) / 2, steal = 0;
            for (int i = 0; i < nums.length;) {
                if (nums[i] <= m) {
                    ++steal;
                    i += 2;
                } else {
                    ++i;
                }
            }
            if (steal >= k) r = m;
            else l = m + 1;
        }
        return l;
    }
}
