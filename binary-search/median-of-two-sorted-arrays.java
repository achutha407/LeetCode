class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);

        int low = 0, high = m, halfLen = (m + n + 1) / 2;
        while (low <= high) {
            int i = (low + high) / 2;
            int j = halfLen - i;

            int leftMax1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int leftMax2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int rightMin1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int rightMin2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (leftMax1 <= rightMin2 && leftMax2 <= rightMin1) {
                if ((m + n) % 2 == 0)
                    return (Math.max(leftMax1, leftMax2) + Math.min(rightMin1, rightMin2)) / 2.0;
                else
                    return Math.max(leftMax1, leftMax2);
            } else if (leftMax1 > rightMin2) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return 0.0;
    }
}
