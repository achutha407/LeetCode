import java.util.*;

class Solution {
    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static class Triple {
        int first;
        Pair second;
        Triple(int first, Pair second) {
            this.first = first;
            this.second = second;
        }
    }

    boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        Set<Integer> sortedQueries = new TreeSet<>();
        for (int query : queries) sortedQueries.add(query);

        Map<Integer, Integer> queryCount = new HashMap<>();
        PriorityQueue<Triple> minheap = new PriorityQueue<>((a, b) -> a.first - b.first);
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        minheap.add(new Triple(grid[0][0], new Pair(0, 0)));
        visited[0][0] = true;
        int[] dir = {-1, 0, 1, 0, -1};

        int count = 0;
        for (int query : sortedQueries) {
            while (!minheap.isEmpty()) {
                Triple curr = minheap.poll();
                int val = curr.first;
                int x = curr.second.first;
                int y = curr.second.second;
                if (val >= query) {
                    minheap.add(curr);
                    break;
                }
                count++;

                for (int i = 0; i < 4; i++) {
                    int newX = x + dir[i];
                    int newY = y + dir[i + 1];
                    if (isValid(newX, newY, m, n) && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        minheap.add(new Triple(grid[newX][newY], new Pair(newX, newY)));
                    }
                }
            }
            queryCount.put(query, count);
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = queryCount.get(queries[i]);
        }
        return res;
    }
}