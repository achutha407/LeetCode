class Solution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        Map<String, Integer> wordToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            wordToIndex.put(words[i], i);
        }

        Map<Integer, List<Integer>> lengthGroups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int len = words[i].length();
            lengthGroups.computeIfAbsent(len, x -> new ArrayList<>()).add(i);
        }

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int len : lengthGroups.keySet()) {
            List<Integer> indices = lengthGroups.get(len);
            Map<String, List<Integer>> maskMap = new HashMap<>();

            for (int idx : indices) {
                String word = words[idx];
                for (int i = 0; i < len; i++) {
                    String masked = word.substring(0, i) + "*" + word.substring(i + 1);
                    maskMap.computeIfAbsent(masked, x -> new ArrayList<>()).add(idx);
                }
            }

            for (List<Integer> candidates : maskMap.values()) {
                for (int i = 0; i < candidates.size(); i++) {
                    for (int j = 0; j < candidates.size(); j++) {
                        if (i == j) continue;
                        int u = candidates.get(i), v = candidates.get(j);
                        if (groups[u] != groups[v] && u < v) {
                            graph[u].add(v);
                        }
                    }
                }
            }
        }

        int[] dp = new int[n];
        int[] next = new int[n];
        Arrays.fill(dp, -1);
        Arrays.fill(next, -1);

        int maxLen = 0, start = 0;
        for (int i = 0; i < n; i++) {
            int len = dfs(i, graph, dp, next);
            if (len > maxLen) {
                maxLen = len;
                start = i;
            }
        }

        List<String> result = new ArrayList<>();
        while (start != -1) {
            result.add(words[start]);
            start = next[start];
        }

        return result;
    }

    private int dfs(int u, List<Integer>[] graph, int[] dp, int[] next) {
        if (dp[u] != -1) return dp[u];
        int max = 1;
        for (int v : graph[u]) {
            int len = 1 + dfs(v, graph, dp, next);
            if (len > max) {
                max = len;
                next[u] = v;
            }
        }
        return dp[u] = max;
    }
}
