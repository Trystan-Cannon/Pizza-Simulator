/**
 * This class represents the culmination of almost all of the other classes
 * in this project: a single pizza.
 * 
 * Each <code>Pizza</code> object contains an <code>ArrayList</code> of
 * <code>Ingredient</code>s that determine its total cost and calorie count.
 * 
 * A shape, either a <code>Circle</code> or <code>Square</code>, is determined
 * for each pizza, dictating the total area which it covers.
 * 
 * A <code>Pizza</code> object also holds a size <code>Fraction</code> which
 * represents the remaining amount of pizza left to be consumed.
 * 
 * All pizzas are randomly generated using the no-args constructor and
 * are comparable by price, size, and calorie count, as well as edible in
 * the sense that their size can be reduced using a method that accepts
 * a <code>Fraction</code> amount to be eaten.
 * 
 * @author Trystan Cannon
 */
public class Pizza implements PizzaComparable {
    
    /**
     * The default circle radius for a randomly generated circle
     * shaped <code>Pizza</code> object.
     */
    public static final int DEFAULT_RANDOM_CIRCLE_RADIUS = 20;
    
    /**
     * The default square side length for a randomly generated square
     * shaped <code>Pizza</code> object.
     */
    public static final int DEFAULT_RANDOM_SQUARE_SIDE_LENGTH = 10;
    
    /**
     * The maximum number of randomly generated ingredients, used in a range,
     * for randomly generating <code>Pizza</code> objects.
     */
    public static final int MAX_NUM_RANDOM_INGREDIENTS = 20;
    
    /**
     * The list of all ingredients that compose this <code>Pizza</code>
     * object.
     */
    private final ArrayList<Ingredient> ingredients;
    
    /**
     * The total cost of this <code>Pizza</code> object.
     * 
     * This is the sum of the cost of all of the ingredients which compose
     * this pizza.
     */
    private final Money totalCost;
    
    /**
     * The remaining amount of pizza expressed as a fraction.
     */
    private Fraction remainingSize = new Fraction(1, 1);
    
    /**
     * The shape of the pizza.
     * 
     * This influences the way in which area is calculated for the pizza.
     * @see getRemainingArea
     */
    private Shape pieShape;
    
    /**
     * The total calorie count of this <code>Pizza</code> object.
     * 
     * This is the sum of the calories of all of the ingredients which
     * compose this pizza.
     */
    private int calorieCount = 0;
    
    /**
     * Constructs a <code>Pizza</code> object with a randomly decided number
     * of randomly generated ingredients as well as a random shape (decided
     * between <code>Circle</code> and <code>Square</code>).
     */
    public Pizza() {
        ingredients   = new ArrayList<>();
        totalCost     = new Money(0, 0);
        pieShape      = Math.random() > 0.5 ?
                           new Circle(0, 0, DEFAULT_RANDOM_CIRCLE_RADIUS) :
                           new Square(0, 0, DEFAULT_RANDOM_SQUARE_SIDE_LENGTH);
        
        // Generate a randomly determined number of ingredients.
        int numIngredients =
                (int)(Math.random() * (MAX_NUM_RANDOM_INGREDIENTS) + 1);
        
        // Add a set of randomly generated ingredients.
        for(int index = 0; index < numIngredients; index++) {
            ingredients.add(generateRandomIngredient());
        }
        
        updateCalorieCountAndTotalCost();
    }
    
    /**
     * Returns the remaining amount of pizza left as a <code>Fraction</code>
     * object.
     * 
     * @return The remaining <code>Fraction</code> of pizza.
     */
    public Fraction getRemaining() {
        return remainingSize;
    }
    
    /**
     * Sets the remaining amount of pizza left to the given
     * <code>Fraction</code> object.
     * 
     * Given any fraction, the initial object given is reduced to its
     * simplest terms before being set as the remaining amount for this
     * <code>Pizza</code> object.
     * 
     * A fractional amount less than zero or greater than one causes this
     * method to throw a <code>PizzaException</code> detailing the error.
     * 
     * Because <code>Fraction</code> objects are immutable, there is no
     * risk for a privacy leak given a parameter that is an object.
     * 
     * @param remaining The remaining <code>Fraction</code> of pizza. This
     *                  value is reduced and is checked for validity: If it
     *                  is less than zero or greater than one, a
     *                  <code>PizzaException</code> is thrown.
     */
    public void setRemaining(Fraction remaining) {
        remaining = remaining.reduce();
        
        if(remaining.compareTo(new Fraction(0, 0)) == -1) {
            throw new PizzaException("Cannot set the remaining size of " +
                                     "a pizza to less than zero.");
        } else if(remaining.compareTo(new Fraction(1, 1)) == 1) {
            throw new PizzaException("Cannot set the remaining size of " +
                                     "a pizza to more than one.");
        }
        
        this.remainingSize = remaining;
    }
    
    /**
     * Returns the current total number of calories for this <code>Pizza</code>
     * object.
     * 
     * Note that this value is not computed in this method, rather the number
     * of calories is updated as ingredients are added and removed from the
     * <code>Pizza</code>.
     * 
     * @return This pizza's total number of calories.
     */
    public int getCalories() {
        return calorieCount;
    }
    
    /**
     * Returns the current total cost of this <code>Pizza</code> object,
     * or rather the total cost of all its ingredients.
     * 
     * Note that this value is not computed in this method, rather the total
     * cost is updated as ingredients are added and removed from the
     * <code>Pizza</code>.
     * 
     * @return This pizza's total cost (the sum of the cost of all its
     *         ingredients).
     */
    public Money getCost() {
        return totalCost.clone(); // Clone to avoid privacy leak.
    }
    
    /**
     * Of the eight leaf ingredient classes, this method creates one and
     * returns it.
     * 
     * Currently the ingredients that may possibly be returned by this method
     * are:
     *      - <code>Alfredo</code>
     *      - <code>Goat</code>
     *      - <code>Marinara</code>
     *      - <code>Mozzarella</code>
     *      - <code>Olive</code>
     *      - <code>Pepper</code>
     *      - <code>Pepperoni</code>
     *      - <code>Sausage</code>
     * 
     * Because of the simple nature of this method, it behaves as O(1).
     * 
     * @return A random <code>Ingredient</code> object.
     */
    private static Ingredient generateRandomIngredient() {
        Ingredient randIngredient = null;
            
        switch ((int) (Math.random() * 8)) {
            case 0:
                randIngredient = new Alfredo();
                break;

            case 1:
                randIngredient = new Goat();
                break;

            case 2:
                randIngredient = new Marinara();
                break;

            case 3:
                randIngredient = new Mozzarella();
                break;

            case 4:
                randIngredient = new Olive();
                break;

            case 5:
                randIngredient = new Pepper();
                break;

            case 6:
                randIngredient = new Pepperoni();
                break;

            case 7:
                randIngredient = new Sausage();
                break;
        }
        
        return randIngredient;
    }
    
    /**
     * Computes and returns the remaining area of this <code>Pizza</code>
     * object.
     * 
     * This calculation is done by computing the <code>double</code> value
     * of the remaining size <code>Fraction</code> and multiplying it by
     * the area of the pizza's shape. Because this involves converting a
     * <code>Fraction</code> to a <code>double</code>
     * 
     * @return The remaining area of this <code>Pizza</code> object.
     */
    public double getRemainingArea() {
        return remainingSize.toDouble() * pieShape.getArea();
    }
    
    /**
     * Sets the <code>Shape</code> of this <code>Pizza</code> to the given
     * <code>Shape</code>.
     * 
     * However, given a <code>null</code> value, a <code>PizzaException</code>
     * is thrown detailing the error.
     * 
     * The given <code>Shape</code> object is cloned when setting the value
     * of this <code>Pizza</code>'s shape instance variable, so privacy leaks
     * are completely avoided when using this method.
     * 
     * @param shape The new desired <code>Shape</code> for this
     *              <code>Pizza</code> object.
     */
    public void setShape(Shape shape) {
        if(shape == null) {
            throw new PizzaException("Cannot set the Shape of a Pizza to " +
                                     "a null value.");
        }
        
        this.pieShape = shape.clone();
    }
    
    /**
     * "Eats" or removes the given <code>Fraction</code> of remaining
     * <code>Pizza</code> by subtracting the given amount from the remaining
     * <code>Fraction</code>.
     * 
     * Given a negative amount to eat, a remaining size of zero, or an amount
     * that would result in a negative remaining size, this method will
     * throw a <code>PizzaException</code> detailing the error that has
     * occurred.
     * 
     * Also, if the amount eaten results in the pizza's remaining amount
     * reaching zero, then a <code>PizzaException</code> is thrown, detailing
     * this error as well.
     * 
     * On the matter of doing the arithmetic between the fractions manually
     * in this method, it would fit a more general case, sure, if the
     * subtraction were implemented in the <code>Fraction</code> class.
     * However, it would be more work and testing and, therefore, time to
     * implement all of the basic operations for <code>Fraction</code> objects.
     * 
     * @param amount The amount of pizza to eat or subtract from the
     *               remaining <code>Fraction</code>.
     */
    public void eatSomePizza(Fraction amount) {
        if(amount.getNumerator() < 0) {
            throw new PizzaException("Cannot eat a negative amount of " +
                                     "Pizza.");
        } else if(remainingSize.getNumerator() == 0) {
            throw new PizzaException("Cannot eat any amount from a Pizza " +
                                     "whose remaining size is zero.");
        }
        
        int ourNum   = remainingSize.getNumerator() * amount.getDenominator();
        int theirNum = amount.getNumerator() * remainingSize.getDenominator();
        
        if(ourNum - theirNum < 0) {
            throw new PizzaException("Cannot eat more than the amount of " +
                                     "remaining Pizza.");
        }
        
        this.remainingSize =
                new Fraction(
                    ourNum - theirNum,
                    remainingSize.getDenominator() * amount.getDenominator()
                ).reduce();
        
        if(this.remainingSize.getNumerator() == 0) {
            throw new PizzaException("Pizza's remaining size has reached " +
                                     "zero.");
        }
    }
    
    /**
     * Adds the given <code>Ingredient</code> to the <code>Pizza</code>,
     * adjusting the total calorie count and cost accordingly.
     * 
     * Given a <code>null</code> ingredient to add, this method will throw
     * a <code>PizzaException</code> detailing the user of the error.
     * 
     * @param ingredient The <code>Ingredient</code> to add to this
     *                   <code>Pizza</code>.
     */
    public void addIngredient(Ingredient ingredient) {
        if(ingredient == null) {
            throw new PizzaException("Cannot add a null Ingredient to " +
                                     "a Pizza.");
        }
        
        ingredients.add(ingredient);
        
        calorieCount += ingredient.getCalorieCount();
        totalCost.add(ingredient.getCost());
    }
    
    /**
     * Iterates over the ingredients of this pizza, totaling the number
     * of calories and cost of each ingredient to provide current values
     * for <code>calorieCount</code> and <code>totalCost</code>.
     * 
     * As stated in the previous sentence, this method merely iterates over
     * the list of ingredients, so it behaves as O(n) where 'n' is the
     * number of ingredients that comprise this <code>Pizza</code> object.
     */
    private void updateCalorieCountAndTotalCost() {
        calorieCount = 0;
        totalCost.setMoney(0, 0);
        
        for(int index = 0; index < ingredients.size(); index++) {
            Ingredient ingredient = ingredients.get(index);
            
            calorieCount += ingredient.getCalorieCount();
            totalCost.add(ingredient.getCost());
        }
    }

    /**
     * Creates and returns a <code>String</code> representation of this
     * <code>Pizza</code> object.
     * 
     * The representation produced is of the format:
     *      Cost: [cost here; <code>getCost().toString()</code>]
     *      Calories: [calorie count here; <code>getCalories()</code>]
     *      Size: [remaining size here; <code>getRemainingArea()</code>]
     *      Ingredients:
     *          <code>ingredient_0.toString()</code>
     *          ...
     *          <code>ingredient_n.toString()</code>
     * 
     * @return A <code>String</code> representation of this <code>Pizza</code>
     *         object that lists its total cost, calorie count, and all
     *         of the ingredients it contains.
     */
    @Override
    public String toString() {
        String retVal = "Cost: " + totalCost + "\nCalories: " + calorieCount +
                        "\nSize: " + getRemainingArea() + "\nIngredients:\n";
        
        for(int index = 0; index < ingredients.size(); index++) {
            retVal += "\t" + ingredients.get(index) + "\n";
        }
        
        return retVal;
    }
    
    /**
     * Compares the given <code>Pizza</code> object to this one based on
     * total cost.
     * 
     * This method is effectively just calling <code>getCost()</code> and
     * using <code>Money</code>'s <code>compareTo</code> method on the
     * cost of this pizza, comparing it to the cost of the other pizza.
     * 
     * Given a <code>null</code> or non-<code>Pizza</code> object, this
     * method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param other The <code>Pizza</code> object to compare to this one
     *              based on price.
     * 
     * @return <code>1</code> if the price of this pizza is greater than that
     *         of the given pizza, <code>0</code> if they are equal, and
     *         <code>-1</code> if it is less.
     */
    @Override
    public int compareTo(Object other) {
        if(other == null || !(other instanceof Pizza)) {
            throw new PizzaException("Cannot compare non-Pizza objects to " +
                                     "a Pizza object.");
        }
        
        return this.totalCost.compareTo(((Pizza) other).totalCost);
    }

    /**
     * Compares the given <code>Pizza</code> object to this one based on
     * remaining area.
     * 
     * This method is effectively just calling <code>getRemainingArea()</code>
     * and scaling it with the fraction remaining, comparing that
     * <code>double</code> value to that of the given <code>Pizza</code>
     * object.
     * 
     * Given a <code>null</code> or non-<code>Pizza</code> object, this
     * method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param other The <code>Pizza</code> object to compare to this one
     *              based on remaining size.
     * 
     * @return <code>1</code> if the remaining size of this pizza is greater
     *         than that of the given pizza, <code>0</code> if they are equal,
     *         and <code>-1</code> if it is less.
     */
    @Override
    public int compareToBySize(Object other) {
        if(other == null || !(other instanceof Pizza)) {
            throw new PizzaException("Cannot compare non-Pizza objects to " +
                                     "a Pizza object.");
        }
        
        Pizza that = (Pizza) other;
        
        if(this.getRemainingArea() > that.getRemainingArea()) {
            return 1;
        } else if(this.getRemainingArea() == that.getRemainingArea()) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Compares the given <code>Pizza</code> object to this one based on
     * total calories.
     * 
     * Put simply, this method compares the values of <code>getCalories</code>
     * between the two pizzas, returning <code>1</code> if the calories
     * of this pizza is greater than that of the given, <code>0</code> if
     * they are equal, and <code>-1</code> if it is less.
     * 
     * Given a <code>null</code> or non-<code>Pizza</code> object, this
     * method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param other The <code>Pizza</code> object to compare to this one
     *              based on price.
     * 
     * @return <code>1</code> if the total calories of this pizza is greater
     *         than that of the given pizza, <code>0</code> if they are equal,
     *         and <code>-1</code> if it is less.
     */
    @Override
    public int compareToByCalories(Object other) {
        if(other == null || !(other instanceof Pizza)) {
            throw new PizzaException("Cannot compare non-Pizza objects to " +
                                     "a Pizza object.");
        }
        
        Pizza that = (Pizza) other;
        
        if(this.calorieCount > that.calorieCount) {
            return 1;
        } else if(this.calorieCount == that.calorieCount) {
            return 0;
        } else {
            return -1;
        }
    }
    
    /*public static void main(String[] args) {
        Pizza p1, p2;
        
        do {
            p1 = new Pizza();
            p2 = new Pizza();
        } while(p1.compareTo(p2) != 0);
    }*/
    
}