import java.io.Serializable;

/**
 * This class represents an amount in USD using
 * two integer values to keep track of dollars and
 * cents.
 * 
 * All dollars and cents will be positive or 0, and
 * cents will never exceed 99.
 * 
 * @author Trystan Cannon
 */
public final class Money implements Comparable, Cloneable, Serializable {
    
    /**
     * The maximum number of cents that a <code>Money</code> object may ever
     * have at any time.
     */
    public static final int MAX_CENTS = 99;
    
    /**
     * The number of dollars currently represented by
     * this object.
     * 
     * This value will never be below 0 but may grow
     * to the maximum value allowable by its type.
     */
    private int dollars = 0;
    
    /**
     * The number of cents currently represented by
     * this object.
     * 
     * This value will never be below 0 and will never
     * grow beyond 99.
     */
    private int cents = 0;
    
    /**
     * Constructs a new <code>Money</code> object with the given number of
     * dollars and zero cents.
     * 
     * Given an invalid number of dollars i.e <code>dol < 0 </code>, this
     * method will throw a <code>RuntimeException</code> detailing the
     * error and the invalid amount provided.
     * 
     * @param dol The number of dollars with which the object will be
     *            initialized.
     */
    public Money(int dol) {
        if(dol < 0) {
            throw new RuntimeException("Illegal dollar amount: " + dol);
        }
        
        this.dollars = dol;
    }
    
    /**
     * Constructs a new <code>Money</code> object with the given number of
     * dollars and the given number of cents.
     * 
     * Given an invalid number of dollars or cents i.e <code>dol < 0</code> or
     * <code>cent < 0</code> or <code>cent > MAX_CENTS</code>, this method
     * will throw a <code>RuntimeException</code> detailing the error and the
     * invalid amounts provided.
     * 
     * @param dol The number of dollars with which the object will be
     *            initialized.
     * 
     * @param cent The number of cents with which the object will be
     *             initialized.
     */
    public Money(int dol, int cent) {
        if(dol < 0 || cent < 0 || cent > MAX_CENTS) {
            throw new RuntimeException("Illegal dollar or cent amount: " +
                                       dol + ", " + cent);
        }
        
        this.dollars = dol;
        this.cents   = cent;
    }
    
    /**
     * Sets the dollar amount for this object with the given amount.
     * 
     * @param dol The new dollar amount for this object.
     * 
     * @return <code>true</code> if the amount given was valid;
     *         <code>false</code> if not.
     */
    public boolean setDollars(int dol) {
        if(dol < 0) {
            return false;
        }
        
        this.dollars = dol;
        return true;
    }
    
    /**
     * Sets the cent amount for this object with the given amount.
     * 
     * @param cent The new cent amount for this object.
     * 
     * @return <code>true</code> if the amount given was valid;
     *         <code>false</code> if not.
     */
    public boolean setCents(int cent) {
        if(cent < 0 || cent > MAX_CENTS) {
            return false;
        }
        
        this.cents = cent;
        return true;
    }
    
    /**
     * Sets the current dollar and cent amounts of this object with the
     * given values.
     * 
     * Given invalid values, no changes are made to the state of the object.
     * 
     * @param dol The new dollar amount for this object.
     * 
     * @param cent The new cent amount for this object.
     */
    public void setMoney(int dol, int cent) {
        int currentDollars = this.dollars;
        
        if(setDollars(dol)) {
            if(setCents(cent) == false) {
                this.dollars = currentDollars;
            }
        }
    }
    
    /**
     * @return The current dollar amount of this object.
     */
    public int getDollars() {
        return this.dollars;
    }
    
    /**
     * @return The current cent amount of this object.
     */
    public int getCents() {
        return this.cents;
    }
    
    /**
     * @return The current money amount for this object, a combination
     *         of the dollar and cent amount i.e 5.75.
     */
    public double getMoney() {
        return this.dollars + (this.cents / 100d);
    }
    
    /**
     * Adds the given dollar amount to the dollar quantity of this object.
     * 
     * Negative amounts are unacceptable and will result in a thrown
     * <code>RuntimeException</code>.
     * 
     * @param dol The amount of dollars to add to the current quantity of
     *            this object.
     */
    public void add(int dol) {
        if(dol >= 0) {
            this.dollars += dol;
        } else {
            throw new RuntimeException("Illegal dollar amount to add: " +
                                       dol);
        }
    }
    
    /**
     * Adds the given dollar and cent amounts to the quantities of this
     * object.
     * 
     * Given a cent amount greater than the maximum will result in
     * the dollar amount being increased by the excess.
     * 
     * Negative amounts are unacceptable and will result in a thrown
     * <code>RuntimeException</code>.
     * 
     * @param dol The amount of dollars to add to the current quantity of
     *            this object.
     * 
     * @param cent The amount of cents to add to the current quantity of
     *             this object.
     */
    public void add(int dol, int cent) {
        add(dol);
        
        if(this.cents + cent >= 0) {
            
            if(this.cents + cent > MAX_CENTS) {
                add((this.cents + cent) / 100);
            }
            
            this.cents = (this.cents + cent) % 100;
        } else {
            throw new RuntimeException("Illegal cent amount to add: " +
                                       cent);
        }
    }
    
    /**
     * Adds the amount of money represented by the given <code>Money</code>
     * object to this one.
     * 
     * @param other The other <code>Money</code> object whose dollar and cent
     *              amounts are to be added to this one's.
     */
    public void add(Money other) {
        add(other.dollars, other.cents);
    }
    
    /**
     * Checks if the dollar and cent quantities of two money
     * objects are equal; if so, the objects are considered equal.
     * 
     * @param other The other <code>Money</code> object which to compare
     *              to this one.
     * 
     * @return <code>true</code> if the dollar and cent amounts of the
     *         given <code>Money</code> object are equal to that of this
     *         one; <code>false</code> if not.
     */
    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Money)) {
            return false;
        }
        
        Money that = (Money) other;
        return this.dollars == that.dollars && this.cents == that.cents;
    }
    
    /**
     * A <code>String</code> representation of this <code>Money</code>
     * object (the amount of money represented by it).
     * 
     * @return A <code>String</code> representation of the object of the
     *         form: <code>"$(dollars).(cents)"</code>.
     */
    @Override
    public String toString() {
        double money = getMoney();
        
        // Add an extra zero if the cent amount is divisble
        // by 10 to get the proper format for money.
        return "$" + money + (this.cents % 10 == 0 ? "0" : "");
    }
    
    /**
     * Compares the given <code>Money</code> object to this one, checking
     * if it contains a greater, equal, or lower sum of money, and returning
     * 1, 0, -1 respectively.
     * 
     * Because <code>Money</code> objects are just convenient containers for
     * a count of dollars and a count of cents, these are the values compared
     * between the two objects.
     * 
     * If this object contains more money, <code>1</code>; if this object
     * contains an equal amount of money, <code>0</code> is returned; if
     * this object contains less money than the given object,
     * <code>-1</code> is returned.
     * 
     * Given a non-<code>Money</code> or <code>null</code> object, a
     * <code>RuntimeException</code> is thrown, detailing the error.
     * 
     * @param other The other <code>Money</code> object to compare to this
     *              one.
     * @return <code>1</code> if this object has more money; <code>0</code>
     *         if this object has an equal amount of money; and
     *         <code>-1</code> if this object has lower amount. A
     *         <code>RuntimeException</code> is thrown if the given object
     *         is not a <code>Money</code> object.
     */
    @Override
    public int compareTo(Object other) {
        if(other == null || !(other instanceof Money)) {
            throw new RuntimeException("Cannot compare non-Money objects to " +
                                       "a Money object.");
        }
        
        Money that = (Money) other;
        
        if(this.dollars > that.dollars ||
          (this.dollars == that.dollars && this.cents > that.cents)) {
            return 1;
        } else if (this.dollars == that.dollars &&
                   this.cents == that.cents) {
            return 0;
        } else {
            return -1;
        }
    }
    
    /**
     * Creates and returns an identical yet independent copy of this
     * <code>Money</code> object.
     * 
     * Because the <code>Money</code> class only deals with primitives, this
     * method is as simple as a call to the class's <code>int, int</code>
     * constructor with the current dollar and cent values as the arguments.
     * 
     * @return An identical yet independent copy of this <code>Money</code>
     *         object.
     */
    @Override
    public Money clone() {
        return new Money(dollars, cents);
    }
    
}