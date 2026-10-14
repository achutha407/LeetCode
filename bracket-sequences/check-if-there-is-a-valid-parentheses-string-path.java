class Solution {
  public boolean hasValidPath(char[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    Boolean[][][] mem = new Boolean[m][n][m + n];
    return hasValidPath(grid, 0, 0, 0, mem);
  }
  private boolean hasValidPath(char[][] grid, int i, int j, int k, Boolean[][][] mem) {
    if (i == grid.length || j == grid[0].length)
      return false;
    k += grid[i][j] == '(' ? 1 : -1;
    if (k < 0)
      return false;
    if (i == grid.length - 1 && j == grid[0].length - 1)
      return k == 0;
    if (mem[i][j][k] != null)
      return mem[i][j][k];
    return mem[i][j][k] = hasValidPath(grid, i + 1, j, k, mem) | 
                          hasValidPath(grid, i, j + 1, k, mem);
  }
}