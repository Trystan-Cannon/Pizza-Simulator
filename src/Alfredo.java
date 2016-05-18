/**
 * This class represents an Alfredo base sauce to be used as a
 * <code>Base</code> for creating <code>Pizza</code> objects.
 * 
 * As it is a child of <code>Base</code> and <code>Ingredient</code>, it
 * does not have any methods other than constructors.
 * 
 * All characteristics are pre-defined as private static class constants to
 * be used in construction.
 * 
 * @author Trystan Cannon
 */
public final class Alfredo extends Base {
    
    /**
     * The static cost of <code>Alfredo</code> objects.
     */
    private static final Money COST = new Money(3, 0);
    
    /**
     * The static calorie count of <code>Alfredo</code> objects.
     */
    private static final int CALORIE_COUNT = 322;
    
    /**
     * The static description of <code>Alfredo</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "Alfredo sauce is melted Parmesan cheese that has " +
            "emulsified butter to form a smooth and rich substance.";
    
    /**
     * Constructs an <code>Alfredo</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     */
    public Alfredo() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION);
    }
    
}
