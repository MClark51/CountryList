import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/***
 * Class to model a Doubly Linked List
 * @author Marco Clark
 * Date Created: 3/31/2022
 * Date Last Modified: 4/16/2022
 */
public class DoublyLinkedList<E> implements List<E>{
    // Data members
    private Node head, tail;
    int size;
    /***
     * Inner Class to model a node
     */
    private class Node{
        E value;
        Node next;
        Node previous;
        /***
         * Constructor with one argument
         * @param initialValue - value of the node
         */
        Node(E initialValue){
            value = initialValue; 
            next = null;
            previous = null;
        }
    }
    /***
     * Constructor with no arguments
     * Sets head and tail equal  to null, size equal to zero
     */
    public DoublyLinkedList() { //O(1)
        head = tail = null;
        size = 0;
    }
    /***
     * Method to add an item at the head of the List
     * @param item - item being added
     * @return - true if added
     */
    public boolean addFirst(E item) { //O(1)
        Node newNode = new Node(item);
        if(head == null) { 
            head = tail = newNode; 
        }
        else { 
            newNode.next = head;
            newNode.previous = null;
            head = newNode;
        }
        size++; 
        return true;
    }
    /***
     * Method to add an item aat the end of the List
     * @param item - item being added
     * @return - true if added
     */
    public boolean addLast(E item) { //O(1)
        Node newNode = new Node(item);
        if(head == null) { 
            head = tail = newNode; 
        }
        else { 
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode; 
        }
        size++; 
        return true;
    }
    /***
     * Method to add an item to  the list
     * @param - item being added
     * @return - true if added
     * Adds the item at the end using addLast()
     */
    public boolean add(E item) { //O(1)
        return addLast(item);
    }
    /***
     * Method to return the first item of the list
     * @return - head of the List
     * @throws NoSuchElementException - if the head is null
     */
    public E getFirst() { //O(1)
        if (head == null)
            throw new NoSuchElementException();
        return head.value;
    }
    /***
     * Method to return the last item of the list
     * @return - tail of the List
     * @throws NoSuchElementException - if the tail is null
     */
    public E getLast() { //O(1)
        if (head == null)
            throw new NoSuchElementException();
        return tail.value;
    }
    /***
     * Method to remove the first item of the List
     * @return - true if removed successfully
     * @throws NoSuchElementException - if the head is null
     */
    public boolean removeFirst() { //O(1)
        if (head == null) 
            throw new NoSuchElementException();
        head = head.next;
        if(head == null) 
            tail=null;
        size--; 
        return true;
    }
    /***
     * Method to remove the last item of the List
     * @return - true if removed successfully
     * @throws NoSuchElementException - if the head is null
     */
    public boolean removeLast() { //O(n)
        if (head == null) 
            throw new NoSuchElementException();
        if(size == 1) 
            return removeFirst();
        Node current = head;
        Node previous = null;
        while(current.next != null) {
            previous = current; 
            current = current.next;
        }
        previous.next = null; 
        tail = previous;
        size--; 
        return true;
    }
    /***
     * Method to return the String reppresentation of the List
     * @return - String of the List
     */
    public String toString() { //O(n)
        String output = "[";
        Node node = head;
        while(node != null) {
            output += node.value + " ";
            node = node.next;
        }
        output += "]";
        return output;
    }
    /***
     * Method to make the Linked list empty
     * Sets size equal to zero, head and tail equal to null
     */
    public void clear() { //O(1)
        head = tail = null; 
        size = 0;
    }
    /***
     * Method to check if the List is empty
     * @return - if the size equals zero
     */
    public boolean isEmpty() { //O(1)
        return (size == 0); 
    }
    /***
     * Method to return the size of the list
     * @return - size of the List
     */
    public int size() { //O(1)
        return size; 
    }

    /***
     * Method to return a DoublyLinkedListIterator that starts iterating at the specified index
     * @param index - index the list starts at
     * @return - Iterator object
     */
    public Iterator<E> iterator(int index){ //O(1)
        return new DoublyLinkedListIterator(index);
    }
    /***
     * Inner class for a DoublyLinkedListIterator
     */
    private class DoublyLinkedListIterator implements ListIterator<E>{
        private Node current = head;
        private Node previous = null;
        /***
         * Constructor with one argument
         * @param index - index the Iterator starts iterating at
         * @throws ArrayIndexOutOfBoundsException if the index is less than -1 or greater than the size of the List
         */
        public DoublyLinkedListIterator(int index){ //O(1) - best  O(n) - worst
            if (index == size-1){
                current = tail;
                previous = tail.previous;
            }
            else if (index < -1 || index > size){
                throw new ArrayIndexOutOfBoundsException();
            }
            //If the index isn't for the head or tail, then use the next() method so the iterator will begin at index when used
            else if (index != -1) {
                for (int i = -1; i < index; i++){
                    this.next();
                }
            }
            else if (index == -1){
                //Do nothing
            }
        }
        /***
         * Method to check if the Iterator has a non-null object after current
         * @return - true if the current object is not null
         */
        public boolean hasNext() { //O(1)
            return (current != null);
        }
        /***
         * Method to return the next element of the Iterator
         * @return - next element of type E in the list
         * @throws NoSuchElementException if current is null
         */
        public E next() { //O(1)
            if(current == null)
                throw new NoSuchElementException();
            E temp = current.value;
            previous = current;
            current = current.next; 
            return temp;
        }
        /***
         * Method to check if the Iterator has a previous element
         * @return - true if the current element is not null
         */
        public boolean hasPrevious() { //O(1)
            return !(current == null);
        }
        /***
         * Method to retrun the previous elemenet of the Iterator
         * @return - previous element of type E in the List
         * Does NOT throw an excpetion due to issues when printing the head
         */
        public E previous() { //O(1)
            E temp = current.value;
            current = current.previous;
            //This conditional block was necessary to prevent issues when 
            //printing the head, as the previous of the head is null
            if (current == null){
                previous = null;
                return temp;
            }
            else {
                previous = current.previous;
                return temp;
            }
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
     * Method that returns a ListIterator that begins at the 0th index of the List
     * @return - ListIterator
     */
    public ListIterator<E> listIterator(){ //O(1)
        return (ListIterator<E>) iterator(-1);
    }
    /***
     * Method that returns a ListIterator that begins at the specified index of the List
     * @param - index that the Iterator starts at
     * @return - ListIterator
     */
    public ListIterator<E> listIterator(int index){ //O(1) - best  O(n) - worst
        return (ListIterator<E>) iterator(index - 1);
    }
}