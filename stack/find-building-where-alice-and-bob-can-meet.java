class Solution {
  public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
    IndexedQuery[] indexedQueries = getIndexedQueries(queries);
    int[] ans = new int[queries.length];
    Arrays.fill(ans, -1);
    List<Integer> stack = new ArrayList<>();
    int heightsIndex = heights.length - 1;

    for (IndexedQuery iq : indexedQueries) {
      int queryIndex = iq.queryIndex;
      int a = iq.a;
      int b = iq.b;
      if (a == b || heights[a] < heights[b]) {
        ans[queryIndex] = b;
      } else {
        while (heightsIndex > b) {
          while (!stack.isEmpty() && heights[stack.get(stack.size() - 1)] <= heights[heightsIndex])
            stack.remove(stack.size() - 1);
          stack.add(heightsIndex--);
        }
        int j = lastGreater(stack, a, heights);
        if (j != -1)
          ans[queryIndex] = stack.get(j);
      }
    }

    return ans;
  }

  private record IndexedQuery(int queryIndex, int a, int b) {}

  private int lastGreater(List<Integer> A, int target, int[] heights) {
    int l = -1, r = A.size() - 1;
    while (l < r) {
      int m = (l + r + 1) / 2;
      if (heights[A.get(m)] > heights[target])
        l = m;
      else
        r = m - 1;
    }
    return l;
  }

  private IndexedQuery[] getIndexedQueries(int[][] queries) {
    IndexedQuery[] iq = new IndexedQuery[queries.length];
    for (int i = 0; i < queries.length; ++i) {
      int a = Math.min(queries[i][0], queries[i][1]);
      int b = Math.max(queries[i][0], queries[i][1]);
      iq[i] = new IndexedQuery(i, a, b);
    }
    Arrays.sort(iq, Comparator.comparing(IndexedQuery::b, Comparator.reverseOrder()));
    return iq;
  }
}
