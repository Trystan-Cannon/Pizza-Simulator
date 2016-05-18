
/**
 * This class implements the ArrayList ADT as
 * described in the "Data Structures: ArrayList-like
 * Structures" assignment for CSS 162 B.
 *
 * Unlike the native Java ArrayList, this class
 * does not specify a definite type for the contents
 * of the ArrayList using a template. Rather, the
 * ArrayLists born from this class store <code>Object</code> references,
 * allowing for any reference-type to be held.
 *
 * Note, though, that this implies any time an object is retrieved from an
 * ArrayList, it must be cast to a different reference-type in order to be used
 * properly.
 *
 * The contents of this data structure is ordered; each object has a numerical
 * index.
 *
 * When an object is retrieved from the list, it is not removed from the list.
 * When an object is added, it moves the indexes around it to fit at the index
 * specified.
 *
 * When an object is removed from the list, it collapses the list around it,
 * changing the indexes of the objects surrounding it.
 *
 * The size of the list grows and shrinks indefinitely until the size is either
 * 0 or reaches a length not sustainable in memory.
 *
 * @author Trystan Cannon
 */
public class ArrayList<T> {

    /**
     * The default number of elements by which the list's array will grow when
     * it needs to expand.
     *
     * If a new element is being added but
     * <code>numElements == list.length</code>, then <code>list</code> will be
     * expanded by the number of elements specified by this value.
     *
     * This value was arbitrarily assigned; no testing or logic was used to
     * decide it.
     *
     * However, my conjecture is that, in a massive list of fractions, it is
     * probably more likely that the number of unique fractions is
     * significantly smaller than their repeated counterparts. Again, though,
     * this is supported by no logic or statistics or magic intuition, only my
     * assumptions about people.
     *
     * Using a smaller number, in the case of a list containing significantly
     * less unique fractions that repeats, this will save both time and space.
     *
     * Conversely, a list with a large number of unique fractions in comparison
     * to repeats, this will likely take more time but equal or less space,
     * seeing as it will expand by a smaller margin, fitting the number of
     * unique fractions with less unused allocated space.
     */
    private static final int DEFAULT_GROWTH_SIZE = 10;

    /**
     * The starting number of elements for the <code>list</code> array that
     * will contain all of the objects added to the list.
     *
     * This value also determines how early or late the array will need to be
     * resized as objects are added.
     *
     * Too small a value will cause the array to be resized sooner, meaning
     * that time and space will need to be spent to do so.
     *
     * Too large a value will cause the array to allocate too much space,
     * meaning that time will not be spent in resizing, but there will be
     * unused space.
     *
     * However, the amount of unused space should influence this starting
     * value, for a smaller amount of unused space due to a large starting
     * value is typically better than no unused space at the cost of time spent
     * resizing the array.
     *
     * @see resize
     */
    private static final int DEFAULT_ARRAY_SIZE = 50;

    /**
     * The array that houses all of the objects stored in this
     * <code>ArrayList</code>.
     */
    private Object[] list = new Object[DEFAULT_ARRAY_SIZE];
    
    /**
     * The current total number of elements in the <code>ArrayList</code>.
     * 
     * This is used for keeping track of the dynamic size of the
     * list, seeing as the <code>list</code> array does not necessarily reflect
     * the current size of the <code>ArrayList</code> as it appears to
     * the user.
     */
    private int numElements = 0;

    /**
     * Sets the object at the given index to the given object, returning the
     * object currently at its place.
     * 
     * Given an invalid index, this method throws a <code>Runtime</code>
     * exception, detailing the error.
     * 
     * @param object The new object to be set at the given index.
     * @param index The index at which to set the new object.
     * 
     * @return The object currently occupying the given index.
     */
    public T set(T object, int index) {
        if (index < 0 || index > numElements) {
            throw new RuntimeException("Failed to insert " + object + " @ "
                    + index);
        }
        
        T element = get(index);
        list[index] = object;
        
        return element;
    }
    
    /**
     * Inserts the given object and the index provided. The object given can be
     * <code>null</code>.
     *
     * Objects cannot be inserted before index 0 or after the size + 1 of the
     * list.
     *
     * @precondition index >= 0
     * @precondition index <= list.size()
     *
     * @param objectToAdd The object to be inserted into the list.
     * @param index The index at which the object will be inserted.
     */
    public void insert(T objectToAdd, int index) {
        if (index < 0 || index > numElements) {
            System.out.println("Failed to insert " + objectToAdd + " @ "
                    + index);
            return;
        }

        numElements++;
        if (numElements >= list.length) {
            resize(DEFAULT_GROWTH_SIZE);
        }

        // Move all current elements after the index given down 1 place.
        for (int place = numElements; place > index; place--) {
            list[place] = list[place - 1];
        }

        list[index] = objectToAdd;
    }

    /**
     * Adds the object given to the end of the <code>ArrayList</code> (inserts
     * it at the first index not in use).
     *
     * This method is merely a wrapper method calling <code>insert</code> with
     * the object given and an index value of the number of elements.
     *
     * @param objectToAdd The object to be inserted at the end of the list.
     */
    public void add(T objectToAdd) {
        insert(objectToAdd, numElements);
    }

    /**
     * Removes and returns the object at the given index. However, if the index
     * provided is out of bounds, then an exception is thrown.
     *
     * @param index The index whose object will be removed and returned.
     * @return The object at the given index who will be removed and returned.
     */
    public T remove(int index) {
        checkIndex(index);
        T objectToReturn = (T) list[index];

        // Collapse the list around the object removed.
        numElements--;
        for (int place = index; place < numElements; place++) {
            list[place] = list[place + 1];
        }

        return objectToReturn;
    }

    /**
     * Returns the object at the index specified. If the index provided is out
     * of bounds, then an exception is thrown.
     *
     * @param index The index of the object in the list to return.
     * @return The object in the list at the given index.
     */
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    /**
     * Attempts to find the index of the object given within the list. If the
     * object isn't found, then <code>-1</code> is returned instead of the
     * index.
     *
     * If there is more than one reference to the object given, then the index
     * of the reference which appears earliest in the list is returned.
     *
     * This is done by iteratively looping through the list, comparing the
     * object provided with those in the list using <code>equals</code>.
     *
     * @param objectToFind The object whose index is to be found in the list.
     * 
     * @return The index of the object found, <code>-1</code> if the object is
     *         not found in the list.
     */
    public int indexOf(Object objectToFind) {
        for (int index = 0; index < numElements; index++) {
            if (list[index].equals(objectToFind)) {
                return index;
            }
        }

        return -1;
    }

    /**
     * Returns the current size of the ArrayList.
     * 
     * @return The current size of this <code>ArrayList</code>.
     */
    public int size() {
        return numElements;
    }

    /**
     * Checks if the ArrayList is empty simply by checking if the number
     * of elements in the list is zero.
     * 
     * @return <code>true</code> if the list has a size of 0;
     *         <code>false</code> if not.
     */
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Compares the size and elements of two <code>ArrayList</code> objects.
     *
     * @param other The other <code>ArrayList</code> to compare against
     *              <code>this</code> one.
     * @return <code>true</code> if the size and objects of <code>this</code>
     *         <code>ArrayList</code> are the same as that of the
     *         <code>ArrayList</code> given; <code>false</code> if not.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ArrayList)) {
            return false;
        }
        if (other == this) {
            return true;
        }

        ArrayList that = (ArrayList) other;

        if (this.numElements != that.numElements) {
            return false;
        }

        for (int index = 0; index < this.numElements; index++) {
            if (!this.list[index].equals(that.list[index])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a <code>String</code> representation of the contents of the
     * ArrayList.
     *
     * @return A string representation of the contents of the list in the form
     * of:      <code>object_1.toString() + ", " + ... object_n.toString()
     *                      + ", ";
     * </code>
     */
    @Override
    public String toString() {
        String retVal = "";

        for (int index = 0; index < numElements; index++) {
            retVal = retVal + list[index] + ", ";
        }

        return retVal;
    }

    /**
     * Resizes the <code>list</code> array by increasing its length by the
     * value given.
     *
     * This is accomplished by simply allocating a new array and iteratively
     * copying over the current contents of the <code>list</code> array.
     *
     * Therefore, as the size of the array grows, the more time and space it
     * will require to resize the array.
     *
     * It is recommended to use a smaller growth value in most cases to reduce
     * the amount of unused space and therefore wasted time/space resizing.
     *
     * @see DEFAULT_GROWTH_SIZE
     *
     * (Postcondition: objects.length == objects.length + growth)
     * (Postcondition: objects[objects.length ... + growth] = null)
     *
     * @param growth The amount by which the <code>objects</code> array will
     *               increase.
     */
    private void resize(int growth) {
        Object[] newArray = new Object[list.length + growth];

        for (int index = 0; index < list.length; index++) {
            newArray[index] = list[index];
        }

        list = newArray;
    }

    /**
     * Checks if the index provided is within the bounds of the
     * <code>ArrayList</code>. If it is out of bounds, than an
     * <code>IndexOutOfBoundsException</code> is thrown from this method.
     *
     * @param index The index to check.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
    }

}