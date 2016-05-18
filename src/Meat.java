/**
 * This class serves as an intermediate parent between <code>Ingredient</code>
 * and any meat <code>Ingredient</code>.
 * 
 * As such, this method does not have any specific methods, only an
 * inherited constructor.
 * 
 * @author Trystan Cannon
 */
public abstract class Meat extends Ingredient {
    
    /**
     * Constructs a new <code>Meat</code> object with the given
     * cost, calorie count, and description.
     * 
     * It is preferred that this constructor go unused, because each
     * child should have its own static set of these characteristics
     * which are used when constructing a new instance.
     * 
     * @param cost The desired cost of the new instance of this meat.
     * @param calorieCount The desired calorie count of the new instance
     *                     of this meat.
     * @param description The short description of this instance of the
     *                    meat's physical characteristics.
     */
    public Meat(Money cost, int calorieCount, String description) {
        super(cost, calorieCount, description);
    }
    
}
