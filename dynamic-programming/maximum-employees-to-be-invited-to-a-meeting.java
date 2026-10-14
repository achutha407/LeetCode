class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];
        for (int f : favorite) inDegree[f]++;
        boolean[] visited = new boolean[n];
        int[] depth = new int[n];
        int maxCircle = 0;
        int totalChain = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            int f = favorite[node];
            depth[f] = Math.max(depth[f], depth[node] + 1);
            if (--inDegree[f] == 0) queue.add(f);
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int len = 0, cur = i;
                while (!visited[cur]) {
                    visited[cur] = true;
                    cur = favorite[cur];
                    len++;
                }
                int start = cur;
                int cycleLen = 1;
                cur = favorite[start];
                while (cur != start) {
                    cycleLen++;
                    cur = favorite[cur];
                }
                if (cycleLen == 2) {
                    int a = favorite[start], b = start;
                    totalChain += 2 + depth[a] + depth[b];
                } else {
                    maxCircle = Math.max(maxCircle, cycleLen);
                }
            }
        }
        return Math.max(maxCircle, totalChain);
    }
}
