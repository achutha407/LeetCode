class Solution {
    public int maximumCandies(int[] candies, long k) {
        int max=0;
        for(int candy:candies)
            max=Math.max(max,candy);

            int low=1;
            int high=max;

            while(low<high){
                int mid=low+(high-low)/2;
                if(canAllocateCandies(candies, k, mid)){
                    low=mid+1;
                }
                else{
                    high=mid;
                }

            }
            return canAllocateCandies(candies, k,low) ? low : low-1;
    }
    public boolean canAllocateCandies(int[] candies, long k, int pileSize){
        if(pileSize==0)
            return true;
        long totalPiles = 0;
        for(int candy:candies){
            totalPiles+= candy/pileSize;
            if(totalPiles >=k)
            return true;
        }
    return false;

    }
}

