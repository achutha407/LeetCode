class FoodRatings {
    // Map from cuisine name to a sorted set of (rating, food) pairs
    private Map<String, TreeSet<Pair<Integer, String>>> cuisineToFoodSet = new HashMap<>();
  
    // Map from food name to (rating, cuisine) pair for quick lookup
    private Map<String, Pair<Integer, String>> foodInfo = new HashMap<>();
  
    // Comparator for sorting foods by rating (descending) and name (ascending)
    private final Comparator<Pair<Integer, String>> foodComparator = (a, b) -> {
        // First compare by rating in descending order
        if (!a.getKey().equals(b.getKey())) {
            return b.getKey().compareTo(a.getKey());
        }
        // If ratings are equal, compare by food name in ascending order
        return a.getValue().compareTo(b.getValue());
    };

    /**
     * Initialize the food rating system with foods, their cuisines, and initial ratings
     * @param foods Array of food names
     * @param cuisines Array of cuisine types corresponding to each food
     * @param ratings Array of initial ratings corresponding to each food
     */
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
          
            // Add food to the cuisine's sorted set
            cuisineToFoodSet.computeIfAbsent(cuisine, k -> new TreeSet<>(foodComparator))
                           .add(new Pair<>(rating, food));
          
            // Store food information for quick lookup
            foodInfo.put(food, new Pair<>(rating, cuisine));
        }
    }

    /**
     * Update the rating of a specific food
     * @param food The name of the food to update
     * @param newRating The new rating value
     */
    public void changeRating(String food, int newRating) {
        // Get current food information
        Pair<Integer, String> currentInfo = foodInfo.get(food);
        int oldRating = currentInfo.getKey();
        String cuisine = currentInfo.getValue();
      
        // Update food information map with new rating
        foodInfo.put(food, new Pair<>(newRating, cuisine));
      
        // Remove old entry from the cuisine's sorted set
        cuisineToFoodSet.get(cuisine).remove(new Pair<>(oldRating, food));
      
        // Add new entry with updated rating to the cuisine's sorted set
        cuisineToFoodSet.get(cuisine).add(new Pair<>(newRating, food));
    }

    /**
     * Get the highest-rated food for a specific cuisine
     * @param cuisine The cuisine type to query
     * @return The name of the highest-rated food in the specified cuisine
     */
    public String highestRated(String cuisine) {
        // Return the food name from the first element (highest rated) in the sorted set
        return cuisineToFoodSet.get(cuisine).first().getValue();
    }
}

