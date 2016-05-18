
/**
 * This class serves as a simple abstraction of
 * a fraction as used in the specification for
 * the FractionsV2 assignment.
 *
 * Beyond the sepcification of the assignment,
 * this class implements three methods:
 *     - gcd          -> Used for reducing fractions
 *     - reduce       -> Reduces fractions into simplest form.
 *     - correcetSign -> Fixes sign format for the fraction.
 *
 * A fraction has both a numerator and denominator,
 * but it only allows for one non-negative member:
 * A fraction cannot have a negative numerator and
 * negative denominator.
 *
 * Fractions are NOT always reported in simplest form.
 * Users MUST reduce the fraction using the
 * <code>reduce</code> method to achieve this. Therefore, it is good practice
 * when using this class to always reduce the fraction before doing anything
 * with it.
 *
 * Currently, no mathematical operations are supported by this class. All
 * operations, then, must be performed by direct manipulation for the numerator
 * and denominator via accessor methods.
 *
 * <code>String</code> representations of <code>Fraction</code> objects are of
 * the following format: <code>"A/B"</code> where 'A' and 'B' are the numerator
 * and denominator values of the fraction, respectively.
 *
 * @see gcd
 * @see reduce
 * @see correctSign
 * 
 * @author Trystan Cannon
 */

public class Fraction implements Comparable {

    private int numerator   = 1;
    private int denominator = 1;

    /**
     * Constructs a <code>Fraction</code> whose numerator and denominator are
     * both 1.
     */
    public Fraction() {
    }

    /**
     * Constructs a <code>Fraction</code> with the given numerator and
     * denominator values.
     *
     * The sign of the fraction is corrected to reflect only the numerator of
     * the fraction as being negative if the fraction is, in fact, negative.
     * 
     * Given a denominator of zero, this method throws a
     * <code>RuntimeException</code> detailing the error.
     *
     * @see correctSign
     * 
     * @param numerator The desired numerator of the fraction.
     * @param denominator The desired denominator of the fraction.
     */
    public Fraction(int numerator, int denominator) {
        if(denominator == 0) {
            throw new RuntimeException("Cannot create a Fraction whose " +
                                       "denominator equals zero.");
        }
        
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Ensures that only the numerator of the fraction is negative if the
     * fraction is, in fact, negative.
     *
     * If both the numerator and denominator are negative, then the values of
     * the numerator and denominator are set to both positive i.e -A/-B -> A/B.
     *
     * This is done to make sure comparison of <code>Fraction</code> objects is
     * done fairly, avoiding comparing two <code>Fraction</code> objects of
     * equal value but failing because the numerator of one is negative, while
     * the denominator of the other is negative instead.
     *
     * Example: -A/B compared to A/-B should return true, however if the signs
     * are not corrected, it will return false. Correcting the signs forces the
     * fractions to appear as both -A/B.
     */
    private void correctSign() {
        if (denominator < 0 && numerator >= 0) {
            denominator = Math.abs(denominator);
            numerator = -1 * numerator;
        } else if (denominator < 0 && numerator == 0) {
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }
    }

    /**
     * @return The current numerator value of the fraction.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * @return The current denominator value of the fraction.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Sets the current numerator value of the fraction with the new given
     * value.
     *
     * @param numerator The new numerator value for the fraction.
     */
    //public void setNumerator(int numerator) {
    //    this.numerator = numerator;
    //}

    /**
     * Sets the current denominator value of the fraction with the new given
     * value.
     *
     * @param denominator The new denominator value for the fraction.
     */
    //public void setDenominator(int denominator) {
    //    this.denominator = denominator;
    //}

    /**
     * Compares the given <code>Fraction</code> to <code>this</code> one for
     * equality.
     *
     * This is done by checking if both the numerator and denominator values are
     * equal to each other. Note, though, that the values checked are acquired
     * through <code>Fraction.getNumerator()</code> and
     * <code>Fraction.getDenominator()</code> to ensure that the signs of the
     * values are corrected for fair comparison.
     *
     * @see correctSign
     * 
     * @param other The <code>Fraction</code> object to compare to this one.
     * 
     * @return If the two <code>Fraction</code> objects are equal.
     */
    public boolean equals(Fraction other) {
        return other != null
                && this.getNumerator() == other.getNumerator()
                && this.getDenominator() == other.getDenominator();
    }

    /**
     * @return The fraction as a String of the form A/B where A and B are the
     *         numerator and denominator values of the fraction, respectively.
     *         These values have corrected signs (the numerator will be the
     *         only negative number if the fraction is negative).
     */
    @Override
    public String toString() {
        return this.getNumerator() + "/" + this.getDenominator();
    }

    /**
     * Reduces the fraction to its simplest terms, but, because
     * <code>Fraction</code> objects are immutable, returns
     * the reduced fraction.
     *
     * If the fraction's denominator is 0, then nothing is changed.
     *
     * The sign of the fraction is corrected before any other changes are made
     * to the value of its numerator and denominator.
     *
     * @see getNumerator
     * @see getDenominator
     * @see correctSign
     * 
     * @return The same <code>Fraction</code> object. This is used to easily
     *         chain instantiation statements with a call to reduce:
     *         <code>Fraction f = new Fraction(a, b).reduce();</code>
     */
    public Fraction reduce() {
        if(numerator == 0) {
            return new Fraction(0, 1);
        }
        
        correctSign();

        Fraction reduced = new Fraction(numerator, denominator);
        int      divisor = gcd(Math.abs(numerator), denominator);

        reduced.numerator   = (numerator / divisor);
        reduced.denominator = (denominator / divisor);

        return reduced;
    }
    
    /**
     * Returns the <code>double</code> value of this <code>Fraction</code>
     * by casting the <code>int</code> numerator to a <code>double</code> and
     * dividing it by the denominator.
     * 
     * This makes for a potentially lossy conversion, so be wary that many
     * <code>Fraction</code>s are not properly represented by their
     * supposed decimal equivalents.
     * 
     * @return The <code>double</code>, or decimal, value of this
     *         <code>Fraction</code>.
     */
    public double toDouble() {
        return (double) numerator / denominator;
    }

    /**
     * Finds the greatest common denominator between two given values using
     * Euclidean's algorithm.
     *
     * See https://en.wikipedia.org/wiki/Euclidean_algorithm for the concept
     * and other implementations.
     *
     * @return The greatest common denominator between the the two given
     *         values.
     */
    private static int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }

        return gcd(num2, num1 % num2);
    }
    
    /**
     * Compares the given <code>Fraction</code> object's numerical value to
     * that of this object; returning 1, 0, or -1 respectively depending on
     * if the given object is less than, equal to, or greater than this one.
     * 
     * Given a <code>null</code> or non-<code>Fraction</code> object, this
     * method will throw a <code>RuntimeException</code> detailing the
     * error.
     * 
     * In its simplest sense, this method reduces the fractions, though
     * perhaps unnecessarily, and then multiplies their numerators by
     * the opposing denominator to given them a common denominator. Then,
     * the two numerators are simply compared numerically.
     * 
     * Therefore, this method behaves as O(1), though the <code>gcd</code>
     * method's Big-O is unknown to me, so, in the case that it is not
     * O(1), then this method's Big-O is that of the <code>gcd</code>
     * method, because it is the only non-constant operation used.
     * 
     * @param other The other <code>Fraction</code> object to compare to
     *              this one.
     * 
     * @return <code>1</code> if this <code>Fraction</code> is greater than
     *         the given object, <code>0</code> if it is equal, or
     *         <code>-1</code> if it is lesser in value.
     */
    @Override
    public int compareTo(Object other) {
        if(other == null || !(other instanceof Fraction)) {
            throw new RuntimeException("Cannot compare non-Fraction objects " +
                                       "with Fractions.");
        }
        
        Fraction that = (Fraction) other;
        
        // Reduce the fractions just in case they haven't been.
        Fraction us   = this.reduce();
        Fraction them = that.reduce();
        
        // Give the fractions a common denominator.
        int ourModifiedNumerator   = us.numerator * them.denominator;
        int theirModifiedNumerator = them.numerator * us.denominator;
        
        if(ourModifiedNumerator > theirModifiedNumerator) {
            return 1;
        } else if (ourModifiedNumerator == theirModifiedNumerator) {
            return 0;
        } else {
            return -1;
        }
    }

}
