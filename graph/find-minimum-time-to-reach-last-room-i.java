import java.util.PriorityQueue;

class Solution {
    private static final int[] dir = {-1, 0, 1, 0, -1};

    static class Node {
        int r, c, time;

        Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    static class Comparator implements java.util.Comparator<Node> {
        public int compare(Node p1, Node p2) {
            return Integer.compare(p1.time, p2.time);
        }
    }

    private boolean isValid(int r, int c, int m, int n) {
        return (r >= 0 && r < m && c >= 0 && c < n);
    }

    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;
        int n = moveTime[0].length;

        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator());
        minHeap.offer(new Node(0, 0, 0));
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();

            if (curr.r == m - 1 && curr.c == n - 1)
                return curr.time;

            for (int i = 0; i < 4; ++i) {
                int newR = curr.r + dir[i];
                int newC = curr.c + dir[i + 1];

                if (isValid(newR, newC, m, n) && !visited[newR][newC]) {
                    Node newNode = new Node(newR, newC, 1 + Math.max(curr.time, moveTime[newR][newC]));
                    minHeap.offer(newNode);
                    visited[newR][newC] = true;
                }
            }
        }
        return -1;
    }
}