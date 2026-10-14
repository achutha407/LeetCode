class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<>();
        int n = words.length;

        int prevGroup = -1;
        for (int i = 0; i < n; i++) {
            if (result.isEmpty() || groups[i] != prevGroup) {
                result.add(words[i]);
                prevGroup = groups[i];
            }
        }

        return result;
    }
}
