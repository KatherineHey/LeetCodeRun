2115. Find All Possible Recipes from Given Supplies [DAG kahn]
https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies

Awesome answer

Assumption: ingredients do not contain any recipe.
1.	For each recipe, count its dependent ingredients as in degree; Store (ingredient, recipes that dependent on it) as HashMap;
2.	Use the supplies as the starting points of topological sort;
3.	Use topogical sort to decrease the in degree of recipes, whenever the in-degree reaches 0, add it to return list.

```
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // Construct directed graph and count in-degrees.
        Map<String, Set<String>> ingredientToRecipes = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < recipes.length; ++i) {
            for (String ing : ingredients.get(i)) {
                ingredientToRecipes.computeIfAbsent(ing, s -> new HashSet<>()).add(recipes[i]);
            }
            inDegree.put(recipes[i], ingredients.get(i).size());
        }
        // Toplogical Sort.
        List<String> ans = new ArrayList<>();
        Queue<String> available = Stream.of(supplies).collect(Collectors.toCollection(LinkedList::new));
        while (!available.isEmpty()) {
            String ing = available.poll();
            if (ingredientToRecipes.containsKey(ing)) {
                for (String rcp : ingredientToRecipes.remove(ing)) {
                    if (inDegree.merge(rcp, -1, Integer::sum) == 0) {
                        available.offer(rcp);
                        ans.add(rcp);
                    }
                }
            }
        }
        return ans;
    }
```
