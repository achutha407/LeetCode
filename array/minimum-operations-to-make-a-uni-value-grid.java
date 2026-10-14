import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        List<Integer> nums = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        
        // Flatten the grid and check divisibility condition
        int remainder = grid[0][0] % x;
        for (int[] row : grid) {
            for (int num : row) {
                if (num % x != remainder) return -1; // Impossible case
                nums.add(num);
            }
        }

        // Sort the array and find the median
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);

        // Calculate the minimum operations
        int operations = 0;
        for (int num : nums) {
            operations += Math.abs(num - median) / x;
        }
        
        return operations;

}
}
