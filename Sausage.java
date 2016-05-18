/**
 * This class represents an Sausage base sauce to be used as a
 * <code>Meat</code> for creating <code>Pizza</code> objects.
 * 
 * As it is a child of <code>Meat</code> and <code>Ingredient</code>, it
 * does not have any methods other than constructors.
 * 
 * All characteristics are pre-defined as private static class constants to
 * be used in construction.
 * 
 * @author Trystan Cannon
 */
public final class Sausage extends Meat {
    
    /**
     * The static cost of <code>Sausage</code> objects.
     */
    private static final Money COST = new Money(4, 50);
    
     /**
     * The static calorie count of <code>Sausage</code> objects.
     */
    private static final int CALORIE_COUNT = 782;
    
    
    /**
     * The static description of <code>Sausage</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "In the United States, Italian sausage most often refers to a " +
            "style of pork sausage noted for being seasoned with fennel " +
            "and/or anise as the primary seasoning.";
    
    /**
     * Constructs an <code>Sausage</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     */
    public Sausage() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION);
    }
    
}
