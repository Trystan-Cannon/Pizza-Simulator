import java.awt.Color;

/**
 * This class represents an Pepper vegetable to be used as a
 * <code>Vegetable</code> for creating <code>Pizza</code> objects.
 * 
 * As it is a child of <code>Vegetable</code> and <code>Ingredient</code>, it
 * does not have any methods other than constructors.
 * 
 * All characteristics are pre-defined as private static class constants to
 * be used in construction.
 * 
 * @author Trystan Cannon
 */
public final class Pepper extends Vegetable  {
    
    /**
     * The static cost of <code>Pepper</code> objects.
     */
    private static final Money COST = new Money(4, 50);
    
    /**
     * The static calorie count of <code>Pepper</code> objects.
     */
    private static final int CALORIE_COUNT = 72;
    
    /**
     * The static description of <code>Pepper</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "(Bell) Pepper is a cultivar group of the species Capsicum " +
            "annuum. Each pepper is sliced into eigths and is fresh and " +
            "crisp.";
    
    /**
     * The static color for all <code>Pepper</code> objects.
     */
    private static final Color COLOR = Color.RED;
    
    /**
     * Constructs an <code>Pepper</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     * @see COLOR
     */
    public Pepper() {
        super(COST, CALORIE_COUNT, DESCRIPTION, COLOR);
    }
    
}
