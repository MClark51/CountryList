import java.util.Iterator;
import java.util.ListIterator;

/***
 * Class to model an ArrayList that implements List
 * @author Marco Clark
 * Date Created: 3/31/2022
 * Date Last Modified: 4/16/2022
 */
public class ArrayList<E> implements List<E> {
    // data members
    private E[] elements;
    private int size;
    // Constructors

    /***
     * Default Constructor with no parameters
     * Makes an empty list with 10 null elements and size equal to zero
     */
    public ArrayList() { //O(1)
        elements = (E[]) new Object[10];
        size = 0;
    }
    /***
     * Constructor with one argument, the capacity of the list
     * @param capacity - capacity to be set
     */
    public ArrayList(int capacity) { //O(1)
        elements = (E[]) new Object[capacity];
        size = 0;
    }
    // Adding an item to the list (2 methods)
    
    /***
     * Method to add an item to the end of the ArrayList
     * @param item - item being added
     * @return - true if added successfully
     */
    public boolean add(E item) { //O(1) up to O(n)
        return add(size, item);
    }
    /***
     * Method to add an item at the given index
     * @param index - index being added at
     * @param item - itme being added
     * @return - true if added successfully
     */
    public boolean add(int index, E item){ //O(n)
        if(index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        ensureCapacity(); //O(n)
        if (size == 0){
            elements[0] = item;
        }else{
            for(int i=size-1; i>index; i--){ //O(n)
                elements[i+1] = elements[i];
            }
            elements[index] = item;
        }
        size++;
        return true;
    }
    // Getter and Setter

    /***
     * Method to return the object at the given index
     * @param index - index that the value is being returned from
     * @return - object at index
     */
    public E get(int index) { //O(1)
        checkIndex(index); //O(1)
        return elements[index];
    }
    /***
     * Method to set the value of the object at the given index
     * @param index - index being set
     * @param item - value being set
     * @return - previous value of the object at the index
     */
    public E set(int index, E item) {
        checkIndex(index); //O(1)
        E oldItem = elements[index];
        elements[index] = item;
        return oldItem;
    }

    /***
     * Method to return the size of ArrayList
     * @return - size of the ArrayList
     */
    public int size() { //O(1)
        return size; 
    } 
    
    /***
     * Method to clear an arraylist
     * Sets the size equal to zero
     */
    public void clear() { //O(1)
        size = 0; 
    } 
    
    /***
     * Method to check if the ArrayList is empty
     * @return - boolean representation of if the size equals zero
     */
    public boolean isEmpty() { //O(1)
        return (size == 0);
    } 

    /***
     * Method to remove an object from the ArrayList
     * @param o - object being removed
     * @return - boolena representation of whether the list contains the object and was successfully removed
     */
    public boolean remove(Object o) { //O(n)
        E item = (E) o;
        for(int i=0; i<size; i++)
            if(elements[i].equals(item)){
                remove(i); //O(n)
                return true;
            }
        return false;
    }
    
    /***
     * Method to remove the object at a specific index
     * @param index - index the object is removed from
     * @return - the item at the index
     */
    public E remove(int index) { //O(n)
        checkIndex(index);
        E item = elements[index];
        for(int i=index; i<size-1; i++)
            elements[i] = elements[i+1];
        size--;
        return item;
    }
    
    /***
     * Method to trim the size of the ArrayList if the size does not equal the length of the elements ArrayList
     */
    public void trimToSize() { //O(n)
        if (size != elements.length) {
            E[] newElements = (E[]) new Object[size];
        for(int i=0; i<size; i++)
            newElements[i] = elements[i];
        elements = newElements;
        }
    }
    
    /***
     * Method to grow the size of the ArrayList if the number of elements exceeds the size of the ArrayList
     */
    private void ensureCapacity() { //O(N)
        if(size >= elements.length) {
            int newCap = (int) (elements.length * 1.5);
            E[] newElements = (E[]) new Object[newCap];
            for(int i=0; i<size; i++)
                newElements[i] = elements[i];
            elements = newElements;
        }
    }
    
    /***
     * Method to check if a index value is valid
     * @param index - index being checked
     * @throws ArrayIndexOutOfBoundsException if the index is less than zero or greater than or equal to the size of the ArrayList
     */
    private void checkIndex(int index){ //O(1)
        if(index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds. Must be between 0 and "+(size-1));
    }
    
    /***
     * Method to return the String reppresentation of the ArrayList
     * @return - String of the ArrayList
     */
    public String toString() { //O(n)
        String output = "[";
        for(int i=0; i<size-1; i++)
            output += elements[i] + " ";
        output += elements[size-1] + "]";
        return output;
    }
    
    /***
     * Method to return an Iterator object of type ArrayListIterator
     * @param - index that the Iterator begins at
     */
    public Iterator<E> iterator(int index){
        return new ArrayListIterator(index);
    }
    
    /***
     * Inner class for an Iterator of type ArrayListIterator
     */
    private class ArrayListIterator implements ListIterator<E>{
        private int current = -1;
        private int previous = -2;
        /***
         * Constructor with one parameter
         * @param index - index the iterator is to start iterating at when first used
         */
        public ArrayListIterator(int index){
            current = index;
            previous = index - 1;
        }
        /***
         * Method to determine if the current element of the iterator has a non-null succsessive element
         * @return - true if the current index is not the last element
         */
        public boolean hasNext() { 
            return current < size-1; 
        }
        /***
         * Method to return the next element
         * @return - the next element of type E in the ArrayList
         */
        public E next() { 
            current++;
            previous++;
            return elements[current]; 
        }
        /***
         * Method to determine if there is a non-null element prior to the current element
         * @return - true if the current element is not the first element
         */
        public boolean hasPrevious() {
            return !(current == 1);
        }
        /***
         * Method to return the previous element
         * @return - the pprevious element of type E in the ArrayList
         */
        public E previous() {
            previous--;
            current --;
            return elements[previous];
        }
        /***
         * Method is not used in this project
         * @throws UnsupportedOperationException - this operation cannot be called in the test class
         */
        public int nextIndex(){
            throw new UnsupportedOperationException();
        }
        /***
         * Method is not used in this project
         * @throws UnsupportedOperationException - this operation cannot be called in the test class
         */
        public int previousIndex(){
            throw new UnsupportedOperationException();
        }
        /***
         * Method is not used in this project
         * @throws UnsupportedOperationException - this operation cannot be called in the test class
         */
        public void set(E value){
            throw new UnsupportedOperationException();
        }
        /***
         * Method is not used in this project
         * @throws UnsupportedOperationException - this operation cannot be called in the test class
         */
        public void add(E value){
            throw new UnsupportedOperationException();
        }
        /***
         * Method is not used in this project
         * @throws UnsupportedOperationException - this operation cannot be called in the test class
         */
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    /***
     * Method that returns a ListIterator that begins at the 0th index of the ArrayList
     * @return - ListIterator
     */
    public ListIterator<E> listIterator(){
        return (ListIterator<E>) iterator(-1);
    }

    /***
     * Method that returns a ListIterator that begins at the specified index of the ArrayList
     * @param - index that the Iterator starts at
     * @return - ListIterator
     */
    public ListIterator<E> listIterator(int index){
        checkIndex(index -1);
        return (ListIterator<E>) iterator(index + 1);
    }

}