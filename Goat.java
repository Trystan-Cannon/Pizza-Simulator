/**
 * This class represents a Goat cheese to be used as a
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
public final class Goat extends Cheese {
    
    /**
     * The static cost of <code>Goat</code> objects.
     */
    private static final Money COST = new Money(3, 0);
    
    /**
     * The static calorie count of <code>Goat</code> objects.
     */
    private static final int CALORIE_COUNT = 408;
    
    /**
     * The static description of <code>Goat</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "Goat cheese is s cheese made from goat's milk.";
    
    /**
     * Constructs an <code>Goat</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     */
    public Goat() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION);
    }
    
}
