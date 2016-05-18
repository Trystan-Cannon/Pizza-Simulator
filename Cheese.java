/**
 * This class serves as an intermediate parent between <code>Ingredient</code>
 * and any cheese <code>Ingredient</code>.
 * 
 * As such, this method does not have any specific methods, only an
 * inherited constructor.
 * 
 * @author Trystan Cannon
 */
public abstract class Cheese extends Ingredient {
    
    /**
     * Constructs a new <code>Cheese</code> object with the given
     * cost, calorie count, and description.
     * 
     * It is preferred that this constructor go unused, because each
     * child should have its own static set of these characteristics
     * which are used when constructing a new instance.
     * 
     * @param cost The desired cost of the new instance of this cheese.
     * @param calorieCount The desired calorie count of the new instance
     *                     of this cheese.
     * @param description The short description of this instance of the
     *                    cheese's physical characteristics.
     */
    public Cheese(Money cost, int calorieCount, String description) {
        super(cost, calorieCount, description);
    }
    
}
