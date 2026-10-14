class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] group = new int[n + 1];
        int groupId = 0;
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                groupId++;
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                group[i] = groupId;
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v : graph[u]) {
                        if (!visited[v]) {
                            visited[v] = true;
                            group[v] = groupId;
                            queue.add(v);
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int g = 1; g <= groupId; g++) {
            List<Integer> component = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (group[i] == g) component.add(i);
            }
            if (!isBipartite(component, graph)) return -1;

            int maxDepth = 0;
            for (int node : component) {
                maxDepth = Math.max(maxDepth, bfs(node, graph, n));
            }
            res += maxDepth;
        }

        return res;
    }

    private int bfs(int start, List<Integer>[] graph, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 1;
        int max = 1;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    max = Math.max(max, dist[v]);
                    queue.add(v);
                }
            }
        }
        return max;
    }

    private boolean isBipartite(List<Integer> nodes, List<Integer>[] graph) {
        Map<Integer, Integer> color = new HashMap<>();
        for (int node : nodes) {
            if (!color.containsKey(node)) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(node);
                color.put(node, 0);
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v : graph[u]) {
                        if (!nodes.contains(v)) continue;
                        if (!color.containsKey(v)) {
                            color.put(v, color.get(u) ^ 1);
                            queue.add(v);
                        } else if (color.get(v) == color.get(u)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
