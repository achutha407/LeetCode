import java.util.*;

class Solution {
    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static boolean countLineIntersections(List<Pair> coordinates) {
        int lines = 0;
        int overlap = 0;
        
        for (Pair coord : coordinates) {
            if (coord.second == 0) overlap--; 
            else overlap++; 
            if (overlap == 0) lines++;
        }
        return lines >= 3;
    }

    public boolean checkValidCuts(int n, int[][] rectangles) {
        List<Pair> yCoordinates = new ArrayList<>();
        List<Pair> xCoordinates = new ArrayList<>();

        for (int[] rectangle : rectangles) {
            yCoordinates.add(new Pair(rectangle[1], 1)); // Start y
            yCoordinates.add(new Pair(rectangle[3], 0)); // End y
            xCoordinates.add(new Pair(rectangle[0], 1)); // Start x
            xCoordinates.add(new Pair(rectangle[2], 0)); // End x
        }

        yCoordinates.sort((a, b) -> a.first == b.first ? Integer.compare(a.second, b.second) : Integer.compare(a.first, b.first));
        xCoordinates.sort((a, b) -> a.first == b.first ? Integer.compare(a.second, b.second) : Integer.compare(a.first, b.first));

        return countLineIntersections(yCoordinates) || countLineIntersections(xCoordinates);
    }
}
