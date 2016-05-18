/**
 * This class represents an Pepperoni base sauce to be used as a
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
public final class Pepperoni extends Meat {
    
    /**
     * The static cost of <code>Pepperoni</code> objects.
     */
    private static final Money COST = new Money(3, 0);
    
     /**
     * The static calorie count of <code>Pepperoni</code> objects.
     */
    private static final int CALORIE_COUNT = 300;
    
    /**
     * The static description of <code>Pepperoni</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "Pepperoni, also known as pepperoni sausage, is an American " +
            "variety of salami, usually made from cured pork and beef mixed " +
            "together. Pepperoni is characteristically soft, slightly smoky," +
            " and bright red in color.";
    
    /**
     * Constructs an <code>Pepperoni</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     */
    public Pepperoni() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION);
    }
    
}
