class Solution {
    static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        List<Integer> patterns = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        generatePatterns(0, 0, m, 0, patterns);

        for (int a : patterns) graph.put(a, new ArrayList<>());

        for (int a : patterns) {
            for (int b : patterns) {
                if (isValid(a, b, m)) graph.get(a).add(b);
            }
        }

        Map<Integer, Integer> dp = new HashMap<>();
        for (int p : patterns) dp.put(p, 1);

        for (int i = 1; i < n; i++) {
            Map<Integer, Integer> newDp = new HashMap<>();
            for (int p : patterns) {
                int total = 0;
                for (int prev : graph.get(p)) {
                    total = (total + dp.get(prev)) % MOD;
                }
                newDp.put(p, total);
            }
            dp = newDp;
        }

        int result = 0;
        for (int val : dp.values()) result = (result + val) % MOD;
        return result;
    }

    private void generatePatterns(int row, int code, int m, int lastColor, List<Integer> patterns) {
        if (row == m) {
            patterns.add(code);
            return;
        }
        for (int c = 1; c <= 3; c++) {
            if (c != lastColor) generatePatterns(row + 1, code * 10 + c, m, c, patterns);
        }
    }

    private boolean isValid(int a, int b, int m) {
        for (int i = 0; i < m; i++) {
            if (a % 10 == b % 10) return false;
            a /= 10;
            b /= 10;
        }
        return true;
    }
}
