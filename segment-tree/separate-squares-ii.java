
import java.util.*;

class SegmentTree {
    private final int n;
    private final int[] xs;
    private final int[] coveredCount;
    private final int[] coveredWidth;

    public SegmentTree(int[] xs) {
        this.xs = xs;
        this.n = xs.length - 1;
        this.coveredCount = new int[4 * n];
        this.coveredWidth = new int[4 * n];
    }

    public void add(int i, int j, int val) {
        add(0, 0, n - 1, i, j, val);
    }

    public int getCoveredWidth() {
        return coveredWidth[0];
    }

    private void add(int treeIndex, int lo, int hi, int i, int j, int val) {
        if (j <= xs[lo] || xs[hi + 1] <= i)
            return;
        if (i <= xs[lo] && xs[hi + 1] <= j) {
            coveredCount[treeIndex] += val;
        } else {
            int mid = (lo + hi) / 2;
            add(2 * treeIndex + 1, lo, mid, i, j, val);
            add(2 * treeIndex + 2, mid + 1, hi, i, j, val);
        }
        if (coveredCount[treeIndex] > 0) {
            coveredWidth[treeIndex] = xs[hi + 1] - xs[lo];
        } else if (lo == hi) {
            coveredWidth[treeIndex] = 0;
        } else {
            coveredWidth[treeIndex] =
                coveredWidth[2 * treeIndex + 1] + coveredWidth[2 * treeIndex + 2];
        }
    }
}

class Solution {
    public double separateSquares(int[][] squares) {
        List<int[]> events = new ArrayList<>();
        TreeSet<Integer> xsSet = new TreeSet<>();

        for (int[] square : squares) {
            int x = square[0];
            int y = square[1];
            int l = square[2];
            events.add(new int[]{y, 1, x, x + l});
            events.add(new int[]{y + l, -1, x, x + l});
            xsSet.add(x);
            xsSet.add(x + l);
        }

        events.sort(Comparator.comparingInt(a -> a[0]));

        int[] xs = xsSet.stream().mapToInt(Integer::intValue).toArray();

        double halfArea = getArea(events, xs) / 2.0;
        long area = 0;
        int prevY = 0;
        SegmentTree tree = new SegmentTree(xs);

        for (int[] e : events) {
            int y = e[0];
            int delta = e[1];
            int xl = e[2];
            int xr = e[3];

            int coveredWidth = tree.getCoveredWidth();
            long areaGain = coveredWidth * (long) (y - prevY);
            if (area + areaGain >= halfArea)
                return prevY + (halfArea - area) / coveredWidth;
            area += areaGain;
            tree.add(xl, xr, delta);
            prevY = y;
        }

        throw new RuntimeException();
    }

    private long getArea(List<int[]> events, int[] xs) {
        long totalArea = 0;
        int prevY = 0;
        SegmentTree tree = new SegmentTree(xs);
        for (int[] e : events) {
            int y = e[0];
            int delta = e[1];
            int xl = e[2];
            int xr = e[3];
            totalArea += tree.getCoveredWidth() * (long) (y - prevY);
            tree.add(xl, xr, delta);
            prevY = y;
        }
        return totalArea;
    }
}