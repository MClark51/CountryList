import java.util.ListIterator;
/***
 * Class to model a List data structure
 * @author Marco Clark
 * Date Created: 3/31/2022
 * Date Last Modified: 4/01/2022
 */
public interface List<E> {
    /***
     * Mehtod to add an item of type E to the list
     * @param value
     * @return - true if added successfully
     */
    public abstract boolean add(E value);
    /***
     * Method to return the size of the list
     * @return - integer value of size
     */
    public abstract int size();
    /***
     * Method to return a ListIterator object positioned at index 0
     * @return
     */
    public abstract ListIterator<E> listIterator();
    /***
     * Method to  return a ListIterator objected positioned at the given index
     * @param index
     * @return
     */
    public abstract ListIterator<E> listIterator(int index);
}
