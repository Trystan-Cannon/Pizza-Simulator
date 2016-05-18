import java.awt.*;

/** Class Shape
 * 
 * This class represents the abstract (although not explicitly declared)
 * blueprint for all Shapes. Every shape defined in this program inherits
 * from this class, reusing simple code and properties to create a well
 * structured class hierarchy.
 * 
 * Each shape, at its most basic level, contains a set of coordinates for
 * the origin of itself or the bounding box in which it lies (a rectangle
 * in which another shape is drawn).
 * 
 * Similarly, every shape possesses an area of some magnitude. Therefore,
 * every shape must override this class's <code>getArea</code> method to
 * properly communicate the area occupied by each <code>Shape</code> object.
 * 
 * Also, each shape must be drawable, meaning that each subclass should
 * override the draw method of this class in order to properly illustrate
 * the shape in the <code>JFrame</code>.
 *
 * @author Rob Nash, comments and some edits by Trystan Cannon
 */
public abstract class Shape extends Object implements Cloneable {

    /**
     * The default color value for all shapes.
     * 
     * This is used when a <code>Shape</code> constructor is called
     * without specifying the desired color of the shape.
     */
    public static final Color DEFAULT_COLOR = Color.ORANGE;
    
    /**
     * The x coordinate of the shape's origin or bounding box.
     */
    private int x = 0;
    
    /**
     * The y coordinate of the shape's origin or bounding box.
     */
    private int y = 0;
    
    /**
     * The current color of this shape.
     */
    private Color color = null;

    /**
     * Constructs a shape with the given coordinates as its origin.
     * 
     * @param a The x coordinate of the shape's origin.
     * @param b The y coordinate of the shape's origin.
     */
    public Shape(int a, int b) {
        x = a;
        y = b;
    }
    
    /**
     * Constructs a shape with the given coordinates as its origin and
     * the given color as its color.
     * 
     * Given a <code>null</code> color, the color is defaulted to
     * the value specified by <code>DEFAULT_COLOR</code>.
     * 
     * @param a The x coordinate of the shape's origin.
     * 
     * @param b The y coordinate of the shape's origin.
     * 
     * @param color The desired color for the shape.
     */
    public Shape(int a, int b, Color color) {
        x = a;
        y = b;
        
        this.color = (color == null ? DEFAULT_COLOR : color);
    }
    
    /**
     * Clones the <code>Shape</code> object, creating and returning an
     * identical yet independent copy.
     * 
     * @return An identical yet independent copy of this <code>Shape</code>.
     */
    @Override
    public abstract Shape clone();

    /**
     * Returns the current color of this shape.
     * 
     * No copying is done in this method because <code>Color</code> is
     * immutable.
     * 
     * @return The current <code>Color</code> for this <code>Shape</code>.
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Sets the current color of this shape to the given color. However,
     * given a <code>null</code> color, the value remains unchanged.
     * 
     * @param color The new desired <code>Color</code> for this
     *              <code>Shape</code>.
     */
    public void setColor(Color color) {
        if(color != null) {
            this.color = color;
        }
    }
    
    /**
     * @return Calculates the area of the shape. Currently returns -1.
     */
    public double getArea() {
        return -1;
    }

    /**
     * Draws the shape to the frame.
     * 
     * @param g The graphics object used to draw the shape to the frame.
     */
    public void draw(Graphics g) {
    }

    /**
     * @return The x coordinate of the shape's origin or bounding box.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y coordinate of the shape's origin or bounding box.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x coordinate of the shape's origin or bounding box.
     * 
     * @param x The new x coordinate of the shape's origin or bounding box.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the shape's origin or bounding box.
     * 
     * @param y The new y coordinate of the shape's origin or bounding box.
     */
    public void setY(int y) {
        this.y = y;
    }
}