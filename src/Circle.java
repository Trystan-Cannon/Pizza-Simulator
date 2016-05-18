import java.awt.Color;

/**
 * This class represents a basic Circle as a child of <code>Shape</code>.
 * 
 * As a child of <code>Shape</code>, every <code>Circle</code> object
 * has its own coordinate pair to represent its origin on the 2D plane,
 * its own <code>java.awt.Color</code> object for its fill color, and,
 * though unique to the <code>Circle</code> class, a radius value.
 * 
 * @author Trystan
 */
public final class Circle extends Shape {
    
    /**
     * The radius value, in pixels, used when drawing the <code>Circle</code>
     * object.
     */
    private int radius = 0;
    
    /**
     * Constructs a new <code>Circle</code> object with the given coordinates
     * as its origin, a default color set in <code>Shape.DEFAULT_COLOR</code>,
     * and a default radius of zero.
     * 
     * Seeing as this constructor sets the initial radius value to zero,
     * when called to draw without changing this property, nothing will
     * draw to the screen, but all of the operations that involve drawing
     * will still execute.
     * 
     * @param x The x coordinate of the circle's origin.
     * @param y The y coordinate of the circle's origin.
     */
    public Circle(int x, int y) {
        super(x, y);
    }
    
    /**
     * Constructs a new <code>Circle</code> object with the given coordinates
     * as its origin, the give radius value as its radius, and the default
     * color set by <code>Shape.DEFAULT_COLOR</code>.
     * 
     * @param x The x coordinate of the circle's origin.
     * @param y The y coordinate of the circle's origin.
     * @param r The radius of the circle.
     */
    public Circle(int x, int y, int r) {
        super(x, y);
        setRadius(r);
    }
    
    /**
     * Constructs a new <code>Circle</code> object with the given coordinates,
     * radius value, and color.
     * 
     * The color given will be used as both the outline and fill color for the
     * circle when drawn.
     * 
     * @param x The x coordinate of the circle's origin.
     * @param y The y coordinate of the circle's origin.
     * @param c The outline and fill color of the circle for use in drawing.
     * @param r The radius of the circle.
     */
    public Circle(int x, int y, Color c, int r) {
        super(x, y, c);
        setRadius(r);
    }
    
    /**
     * Sets the radius of this circle to the given value.
     * 
     * Given an illegal value, <code>radius <= 0</code>, this method
     * will throw a <code>RuntimeException</code>, detailing this error.
     * 
     * @param radius The new radius for the circle.
     */
    public void setRadius(int radius) {
        if(radius <= 0) {
            throw new RuntimeException("Cannot create a circle with radius " +
                                       "<= 0.");
        }
        
        this.radius = radius;
    }
    
    /**
     * Returns the current radius value for this circle.
     * 
     * @return The current radius value for this circle.
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * Computes and returns the area of this circle using the formula
     * <code>Math.PI * r^2</code>.
     * 
     * @return The current area of this circle.
     */
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    /**
     * Creates and returns an identical yet independent copy of this
     * <code>Circle</code> object.
     *
     * @return An identical yet independent copy of this circle.
     */
    @Override
    public Circle clone() {
        return new Circle(getX(), getY(), getColor(), radius);
    }
    
}
