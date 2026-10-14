import java.util.*;

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> resultSet = new HashSet<>();
        int[] count = new int[10];
        
        // Count digit frequencies
        for (int d : digits) {
            count[d]++;
        }

        // Try all 3-digit combinations
        for (int i = 100; i <= 999; i += 2) { // step by 2 to ensure even numbers
            int a = i / 100;             // hundreds
            int b = (i / 10) % 10;       // tens
            int c = i % 10;              // ones

            // Count needed for this number
            int[] needed = new int[10];
            needed[a]++;
            needed[b]++;
            needed[c]++;

            boolean canForm = true;
            for (int d = 0; d < 10; d++) {
                if (needed[d] > count[d]) {
                    canForm = false;
                    break;
                }
            }

            if (canForm) {
                resultSet.add(i);
            }
        }

        // Convert result set to sorted array
        int[] result = resultSet.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(result);
        return result;
    }
}
