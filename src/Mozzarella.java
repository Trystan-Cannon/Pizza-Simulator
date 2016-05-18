/**
 * This class represents a Mozzarella cheese to be used as a
 * <code>Cheese</code> for creating <code>Pizza</code> objects.
 * 
 * As it is a child of <code>Cheese</code> and <code>Ingredient</code>, it
 * does not have any methods other than constructors.
 * 
 * All characteristics are pre-defined as private static class constants to
 * be used in construction.
 * 
 * @author Trystan Cannon
 */
public final class Mozzarella extends Cheese {
    
    /**
     * The static cost of <code>Mozzarella</code> objects.
     */
    private static final int CALORIE_COUNT = 360;
    
    /**
     * The static calorie count of <code>Mozzarella</code> objects.
     */
    private static final Money COST = new Money(2, 25);
    
    /**
     * The static description of <code>Mozzarella</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "A southern Italian cheese traditionally made from Italian" +
            "buffalo milk by the pasta filata method.";
    
    /**
     * Constructs an <code>Mozzarella</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     */
    public Mozzarella() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION);
    }
    
}
