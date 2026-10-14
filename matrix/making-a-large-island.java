class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int color = 2;
        Map<Integer, Integer> areaMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, color, dirs);
                    areaMap.put(color++, area);
                }
            }
        }

        int max = 0;
        for (int val : areaMap.values()) max = Math.max(max, val);
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    hasZero = true;
                    Set<Integer> seen = new HashSet<>();
                    int sum = 1;
                    for (int[] d : dirs) {
                        int ni = i + d[0], nj = j + d[1];
                        if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] > 1) {
                            int c = grid[ni][nj];
                            if (seen.add(c)) {
                                sum += areaMap.get(c);
                            }
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
        }

        return hasZero ? max : n * n;
    }

    private int dfs(int[][] grid, int i, int j, int color, int[][] dirs) {
        int n = grid.length;
        grid[i][j] = color;
        int area = 1;
        for (int[] d : dirs) {
            int ni = i + d[0], nj = j + d[1];
            if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] == 1) {
                area += dfs(grid, ni, nj, color, dirs);
            }
        }
        return area;
    }
}
