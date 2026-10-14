import java.util.*;

class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        
        for (int key : map.keySet()) {
            int count = map.get(key);
            int groupSize = key + 1;
            int groups = (count + groupSize - 1) / groupSize;
            result += groups * groupSize;
        }
        
        return result;
    }
}
