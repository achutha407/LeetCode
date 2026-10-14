import java.util.*;

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
            Map<String, Integer> indegree = new HashMap<>();
                    Map<String, List<String>> graph = new HashMap<>();
                            Set<String> available = new HashSet<>(Arrays.asList(supplies));

                                    // Initialize graph and indegree
                                            for (int i = 0; i < recipes.length; i++) {
                                                        String recipe = recipes[i];
                                                                    indegree.put(recipe, ingredients.get(i).size());
                                                                                for (String ing : ingredients.get(i)) {
                                                                                                graph.computeIfAbsent(ing, x -> new ArrayList<>()).add(recipe);
                                                                                                            }
                                                                                                                    }

                                                                                                                            // Start with supplies in the queue
                                                                                                                                    Queue<String> queue = new LinkedList<>(available);
                                                                                                                                            List<String> result = new ArrayList<>();

                                                                                                                                                    // Process ingredients using topological sorting
                                                                                                                                                            while (!queue.isEmpty()) {
                                                                                                                                                                        String item = queue.poll();
                                                                                                                                                                                    if (indegree.containsKey(item)) {
                                                                                                                                                                                                    result.add(item); // Add the valid recipe to result
                                                                                                                                                                                                                }
                                                                                                                                                                                                                            if (graph.containsKey(item)) {
                                                                                                                                                                                                                                            for (String neighbor : graph.get(item)) {
                                                                                                                                                                                                                                                                indegree.put(neighbor, indegree.get(neighbor) - 1);
                                                                                                                                                                                                                                                                                    if (indegree.get(neighbor) == 0) {
                                                                                                                                                                                                                                                                                                            queue.offer(neighbor);
                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                                                                                                                                                            return result;
                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                }