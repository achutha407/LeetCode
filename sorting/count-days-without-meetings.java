import java.util.*;

class Solution {
    public int countDays(int days, int[][] meetings) {
        if (meetings.length == 0) return days; // No meetings, all days are free
        
        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        int occupiedDays = 0;
        int prevStart = meetings[0][0], prevEnd = meetings[0][1];

        for (int i = 1; i < meetings.length; i++) {
            int start = meetings[i][0], end = meetings[i][1];

            if (start <= prevEnd + 1) {  // Merge intervals
                prevEnd = Math.max(prevEnd, end);
            } else {  // Count non-overlapping interval
                occupiedDays += (prevEnd - prevStart + 1);
                prevStart = start;
                prevEnd = end;
            }
        }

        // Count the last merged interval
        occupiedDays += (prevEnd - prevStart + 1);

        return days - occupiedDays;
    }
}
