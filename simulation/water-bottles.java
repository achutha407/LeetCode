class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalBottlesDrunk = numBottles;
      
        while (numBottles >= numExchange) {
            totalBottlesDrunk++;
    
            numBottles = numBottles - (numExchange - 1);
        }
      
        return totalBottlesDrunk;
    }
}
