class Solution {
    private int boardSize;

    // Method to find the shortest path to reach the last square
    public int snakesAndLadders(int[][] board) {
        boardSize = board.length; // Get the size of the board (n x n)
        Deque<Integer> queue = new ArrayDeque<>(); // A queue to perform BFS
        queue.offer(1); // Start from the first square
        boolean[] visited = new boolean[boardSize * boardSize + 1]; // Visited array to keep track of visited squares
        visited[1] = true; // Mark the first square as visited
        int moves = 0; // Moves counter
      
        // Perform BFS to reach the last square
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; --i) { // Iterate over current level
                int current = queue.poll(); // Get the current square
                if (current == boardSize * boardSize) { // Check if reached the end
                    return moves;
                }
                // Explore the next 6 possible moves
                for (int k = current + 1; k <= Math.min(current + 6, boardSize * boardSize); ++k) {
                    int[] position = convertToPosition(k); // Convert square number to board coordinates
                    int next = k; // Next square
                    // Check if there's a snake or ladder in the square
                    if (board[position[0]][position[1]] != -1) {
                        next = board[position[0]][position[1]]; // Go to the new square
                    }
                    // If it's not visited, mark as visited and add to the queue
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++; // Increment move count after finishing one level in BFS
        }
        return -1; // Return -1 if it's impossible to reach the end
    }

    // Convert the square number to board coordinates (i, j)
    private int[] convertToPosition(int squareNum) {
        int row = (squareNum - 1) / boardSize;
        int col = (squareNum - 1) % boardSize;
        // In even rows (from the top), the order is right to left
        if (row % 2 == 1) {
            col = boardSize - 1 - col; 
        }
        // Convert row to the actual row in the board from the bottom
        return new int[] {boardSize - 1 - row, col};
    }
}