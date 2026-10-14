import java.util.*;

class Solution {
    private int[] parent;

    private int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]); // Path compression
    }

    private void merge(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a; // Union
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        Map<Integer, Integer> ans = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        // Union all edges
        for (int[] e : edges) merge(e[0], e[1]);

        // Compute minimum AND cost for each component
        for (int[] e : edges) {
            int x = find(e[0]);
            ans.put(x, ans.getOrDefault(x, e[2]) & e[2]);
        }

        // Process queries
        for (int[] q : query) {
            if (q[0] == q[1]) {
                res.add(0);
                continue;
            }
            int a = find(q[0]);
            int b = find(q[1]);

            if (a != b) res.add(-1);
            else res.add(ans.getOrDefault(a, -1));
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
