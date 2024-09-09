package a02a.e1;

import java.util.HashMap;
import java.util.Map;

public class DietFactoryImpl implements DietFactory {

    @Override
    public Diet standard() {
        return new Diet() {

            private final Map<String, Integer> caloriesPer100gMap = new HashMap<>();
            private static final int MIN_CALORIES = 1500;
            private static final int MAX_CALORIES = 2000;

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {

                int totalCalories = 0;
                if (nutritionMap.containsKey(Nutrient.CARBS)) {
                    totalCalories += nutritionMap.get(Nutrient.CARBS) * 4;
                }
                if (nutritionMap.containsKey(Nutrient.PROTEINS)) {
                    totalCalories += nutritionMap.get(Nutrient.PROTEINS) * 4;
                }
                if (nutritionMap.containsKey(Nutrient.FAT)) {
                    totalCalories += nutritionMap.get(Nutrient.FAT) * 9;
                }
                caloriesPer100gMap.put(name, totalCalories);
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                int totalCalories = 0;
                for (Map.Entry<String, Double> entry : dietMap.entrySet()) {
                    String foodName = entry.getKey();
                    double quantityInGrams = entry.getValue();

                    Integer caloriesPer100g = caloriesPer100gMap.get(foodName);
                    if (caloriesPer100g == null) {
                        throw new IllegalArgumentException("Food not found: " + foodName);
                    }

                    double caloriesForQuantity = (caloriesPer100g * quantityInGrams) / 100;
                    totalCalories += caloriesForQuantity;
                }
                return totalCalories >= MIN_CALORIES && totalCalories <= MAX_CALORIES;
            }

        };
    }

    @Override
    public Diet lowCarb() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lowCarb'");
    }

    @Override
    public Diet highProtein() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'highProtein'");
    }

    @Override
    public Diet balanced() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'balanced'");
    }

}
