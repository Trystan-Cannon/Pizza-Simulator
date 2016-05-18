/**
 * This class serves as an intermediate parent between <code>Ingredient</code>
 * and any base (sauce) <code>Ingredient</code>, such as <code>Alfredo</code>
 * or <code>Marinara</code>.
 * 
 * As such, this method does not have any specific methods, only an
 * inherited constructor.
 * 
 * @author Trystan Cannon
 */
public abstract class Base extends Ingredient {
    
    /**
     * Constructs a new <code>Base</code> object with the given
     * cost, calorie count, and description.
     * 
     * It is preferred that this constructor go unused, because each
     * child should have its own static set of these characteristics
     * which are used when constructing a new instance.
     * 
     * @param cost The desired cost of the new instance of this base.
     * @param calorieCount The desired calorie count of the new instance
     *                     of this base.
     * @param description The short description of this instance of the
     *                    base's physical characteristics.
     */
    public Base(Money cost, int calorieCount, String description) {
        super(cost, calorieCount, description);
    }
    
}
