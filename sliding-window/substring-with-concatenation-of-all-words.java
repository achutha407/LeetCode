import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String word = s.substring(j, j + wordLen);
                if (wordCount.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;
                    while (seen.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                    if (count == words.length) res.add(left);
                } else {
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }
        return res;
    }
}
