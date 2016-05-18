import java.awt.Color;

/**
 * This class represents an Olive vegetable to be used as a
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
public final class Olive extends Vegetable {
    
    /**
     * The static cost of <code>Olive</code> objects.
     */
    private static final Money COST = new Money(3, 75);
    
    /**
     * The static calorie count of <code>Olive</code> objects.
     */
    private static final int CALORIE_COUNT = 16;
    
    /**
     * The static description of <code>Olive</code> objects' physical
     * characteristics.
     */
    private static final String DESCRIPTION =
            "An Olive is a small black drupe.";
    
    /**
     * The static color for all <code>Olive</code> objects.
     */
    private static final Color COLOR = Color.BLACK;
    
    /**
     * Constructs an <code>Olive</code> object with the pre-defined
     * class constant characteristics.
     * 
     * @see COST
     * @see CALORIE_COUNT
     * @see DESCRIPTION
     * @see COLOR
     */
    public Olive() {
        super(COST.clone(), CALORIE_COUNT, DESCRIPTION, COLOR);
    }
    
}
