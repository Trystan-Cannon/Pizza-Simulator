/**
 * CSS 162, Pizza Manager Project
 * 
 * This interface, provided solely for the <code>Pizza</code> class,
 * allowing for <code>Pizza</code> objects to be comparable by price,
 * remaining size, and calories.
 * 
 * As an interface, no methods are actually implemented here, only their
 * headers are defined here.
 * 
 * @author: Rob Nash with minor edits by Johnny Lin and comments by
 *           Trystan Cannon
 */
public interface PizzaComparable extends Comparable {

    /**
     * Compares the given <code>Pizza</code> object to this one based on
     * total cost.
     * 
     * This method is effectively just calling <code>getCost()</code> and
     * using <code>Money</code>'s <code>compareTo</code> method on the
     * cost of this pizza, comparing it to the cost of the other pizza.
     * 
     * Given a <code>null</code> or non-<code>Pizza</code> object, this
     * method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param o The <code>Pizza</code> object to compare to this one
     *              based on price.
     * 
     * @return <code>1</code> if the price of this pizza is greater than that
     *         of the given pizza, <code>0</code> if they are equal, and
     *         <code>-1</code> if it is less.
     */
    @Override
    public int compareTo(Object o);

    /**
     * Compares the given <code>Pizza</code> object to this one based on
     * remaining size.
     * 
     * This method is effectively just calling <code>getRemaining()</code> and
     * using <code>Fraction</code>'s <code>compareTo</code> method on the
     * remaining fraction of this pizza, comparing it to the remaining fraction
     * of the other pizza.
     * 
     * Given a <code>null</code> or non-<code>Pizza</code> object, this
     * method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param o The <code>Pizza</code> object to compare to this one
     *              based on remaining size.
     * 
     * @return <code>1</code> if the remaining size of this pizza is greater
     *         than that of the given pizza, <code>0</code> if they are equal,
     *         and <code>-1</code> if it is less.
     */
    public int compareToBySize(Object o);    //a.k.a. compareToByAreaLeft

    /**
     * Compares the given <code>Pizza</code> object to this one based on
     * total calories.
     * 
     * Put simply, this method compares the values of <code>getCalories</code>
     * between the two pizzas, returning <code>1</code> if the calories
     * of this pizza is greater than that of the given, <code>0</code> if
     * they are equal, and <code>-1</code> if it is less.
     * 
     * Given a <code>null</code> or non-<code>Pizza</code> object, this
     * method will throw a <code>PizzaException</code> detailing the
     * error.
     * 
     * @param o The <code>Pizza</code> object to compare to this one
     *              based on price.
     * 
     * @return <code>1</code> if the total calories of this pizza is greater
     *         than that of the given pizza, <code>0</code> if they are equal,
     *         and <code>-1</code> if it is less.
     */
    public int compareToByCalories(Object o);

}
