class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = pos[nums1[i]];
        }

        FenwickTree leftTree = new FenwickTree(n);
        int[] leftCount = new int[n];
        for (int i = 0; i < n; i++) {
            leftCount[i] = leftTree.query(idx[i]);
            leftTree.update(idx[i], 1);
        }

        FenwickTree rightTree = new FenwickTree(n);
        int[] rightCount = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            rightCount[i] = rightTree.query(n - 1) - rightTree.query(idx[i]);
            rightTree.update(idx[i], 1);
        }

        long result = 0;
        for (int i = 0; i < n; i++) {
            result += (long) leftCount[i] * rightCount[i];
        }

        return result;
    }

    class FenwickTree {
        int[] tree;
        int size;

        FenwickTree(int n) {
            size = n;
            tree = new int[n + 1];
        }

        void update(int i, int delta) {
            i++;
            while (i <= size) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        int query(int i) {
            i++;
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
