/**
 * This class represents an Marinara base sauce to be used as a
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

public final class Marinara extends Base {
    
    /**
     * The static cost of <code>Marinara</code> objects.
     */
    private static final Money COST = new Money(2, 50);
    
    /**
     * The static calorie count of <code>Marinara</code> objects.
     */
    private static final int CALORIE_COUNT = 260;
    
    /**
     * The static description of <code>Marinara</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "Marinara sauce is an Italian sauce that originated in Naples, " +
            "usually made with tomatoes, garlic, herbs, and onions. " +
            "Its many variations can include the addition of capers, " +
            "olives and spices. It is occasionally sweetened with a dash " +
            "of red wine.";
    
    /**
     * Constructs an <code>Marinara</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     */
    public Marinara() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION);
    }
    
}
