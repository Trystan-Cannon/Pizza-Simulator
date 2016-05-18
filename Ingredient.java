/**
 * This class serves as the abstract parent from which all other ingredients
 * are derived.
 * 
 * In the most basic sense, an ingredient is represented by its cost,
 * calorie count, and a short description regarding the physical
 * characteristics of the item.
 * 
 * Ingredients are comparable based on all of these qualities when it
 * comes to strict equality, but when compared for "before" or "after,"
 * only the price is used.
 * 
 * @author Trystan Cannon
 */
public abstract class Ingredient implements Comparable {
    
    /**
     * The total cost of this ingredient.
     */
    private Money cost;
    
    /**
     * The calorie count contained in one instance of this ingredient.
     */
    private int calorieCount;
    
    /**
     * A short description of the physical characteristics of the ingredient.
     */
    private String description;
    
    /**
     * Constructs a new <code>Ingredient</code> object with the given
     * cost, calorie count, and description.
     * 
     * It is preferred that this constructor go unused, because each
     * child should have its own static set of these characteristics
     * which are used when constructing a new instance.
     * 
     * @param cost The desired cost of the new instance of this ingredient.
     * @param calorieCount The desired calorie count of the new instance
     *                     of this ingredient.
     * @param description The short description of this instance of the
     *                    ingredient's physical characteristics.
     */
    public Ingredient(Money cost, int calorieCount, String description) {
        setCost(cost);
        setCalorieCount(calorieCount);
        setDescription(description);
    }
    
    /**
     * Returns the current total cost of this ingredient.
     * 
     * @return The current total cost of this ingredient.
     */
    public Money getCost() {
        return cost.clone();
    }
    
    /**
     * Returns the current total calorie count of this ingredient.
     * 
     * @return The current calorie count of this ingredient.
     */
    public int getCalorieCount() {
        return calorieCount;
    }
    
    /**
     * Returns the description regarding the physical characteristics of
     * this ingredient.
     * 
     * @return The description regarding the physical characteristics of
     *         this ingredient.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the cost of this ingredient to the new desired cost.
     * 
     * Given an invalid cost (<code>null</code>), this method will throw
     * a <code>PizzaException</code>, detailing the error.
     * 
     * @param cost The new desired cost for this ingredient.
     */
    private void setCost(Money cost) {
        if(cost == null) {
            throw new PizzaException("Cannot set the cost of an Ingredient " +
                                     "to null.");
        }
        
        this.cost = cost.clone();
    }
    
    /**
     * Sets the calorie count of this ingredient to the new desired calorie
     * count.
     * 
     * Given an invalid count (<code><=0</code>), this method will throw
     * a <code>PizzaException</code>, detailing the error.
     * 
     * @param calorieCount The new desired calorie count for this ingredient.
     */
    private final void setCalorieCount(int calorieCount) {
        if(calorieCount <= 0) {
            throw new PizzaException("Cannot set the calorie count of an " +
                                     "Ingredient to a number <= zero.");
        }
        
        this.calorieCount = calorieCount;
    }
    
    /**
     * Sets the description of this ingredient's physical characteristics to
     * the new desired description.
     * 
     * Given an invalid description (<code>null</code> or
     * <code>length() == 0</code>), this method will throw a
     * <code>PizzaException</code>, detailing the error.
     * 
     * @param description The new desired description of this ingredient's
     *                    physical characteristics.
     */
    private final void setDescription(String description) {
        if(description == null || description.length() == 0) {
            throw new PizzaException("Cannot set the description of an " +
                                     "Ingredient to null or an empty " +
                                     "String.");
        }
        
        this.description = description;
    }
    
    /**
     * Returns a <code>String</code> representation of this ingredient,
     * detailing the cost, calorie count, and description.
     * 
     * @return A <code>String</code> representation of this ingredient.
     */
    @Override
    public String toString() {
        return description + "; cost: " + cost.toString() +
               "; calories: " + calorieCount;
    }
    
    /**
     * Compares the given ingredient to this one based on price, returning
     * <code>1</code> if the price of this ingredient is greater,
     * <code>0</code> if it is equal, and <code>-1</code> if it is less.
     * 
     * Given a <code>null</code> or non-<code>Ingredient</code> object,
     * this method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param other The other <code>Ingredient</code> object to compare to
     *              this one.
     * 
     * @return <code>1</code> if the price of this ingredient is greater,
     *         <code>0</code> if it is equal, and <code>-1</code> if it is
     *         less than that of the given ingredient.
     */
    @Override
    public int compareTo(Object other) {
        if(other == null || !(other instanceof Ingredient)) {
            throw new PizzaException("Cannot compare the cost of " +
                                     "non-Ingredient objects to an " +
                                     "ingredient object.");
        }
        
        return this.cost.compareTo(((Ingredient) other).cost);
    }
    
    /**
     * Compares the given ingredient to this one for strict equality, meaning
     * that their cost, calorie count, and descriptions must be the same.
     * 
     * Given a <code>null</code> or non-<code>Ingredient</code> object,
     * this method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param other The other <code>Ingredient</code> object to compare to
     *              this one for strict equality.
     * 
     * @return <code>true</code> if the given ingredient's cost, calorie
     *         count, and description are the same as this ingredient;
     *         <code>false</code> if not.
     */
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Ingredient)) {
            throw new PizzaException("Cannot compare non-Ingredient objects " + 
                                     "to an Ingredient object.");
        }
        
        Ingredient that = (Ingredient) other;
        
        return this.cost.equals(that.cost) &&
               this.calorieCount == that.calorieCount &&
               this.description.equals(that.description);
    }
    
}
