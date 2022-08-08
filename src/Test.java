import java.io.File;
import java.io.FileNotFoundException;
import java.util.ListIterator;
import java.util.Scanner;

/***
 * Class to test ArrayList, LinkedList, and DoublyLinkedLists and print them both forwards and backwards
 * @author Marco Clark
 * Date Created: 3/31/2022
 * Date Last Modified: 4/16/2022
 */
public class Test {

    /***
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> ar = new ArrayList<>();
        LinkedList<String> ll = new LinkedList<>();
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        readFromFile(ar, "countries.txt");
        readFromFile(ll, "countries.txt");
        readFromFile(dll, "countries.txt");
        System.out.println("Array List (Forward):");
        printListForward(ar);
        System.out.println("\nLinked List (Forward):");
        printListForward(ll);
        System.out.println("\nDoubly Linked List (Forward):");
        printListForward(dll);
        
        System.out.println("\nArray List (Backward):");
        printListBackward(ar);
        System.out.println("\nLinked List (Backward):");
        printListBackward(ll);
        System.out.println("\nDoubly Linked List (Backward):");
        printListBackward(dll);
    }

    /***
     * Method to read from a file and add all of the lines to a List type
     * @param list - List being added to
     * @param filename - file being read from
     */
    public static void readFromFile(List<String> list, String filename){
        File f = new File(filename);
        try{
            Scanner read = new Scanner(f);
            while (read.hasNextLine()){
                list.add(read.nextLine());
            }
            read.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    /***
     * Method to print a list forward
     * @param <E> - Data type being printed
     * @param list - list being printed
     */
    public static <E> void printListForward(List<E> list){
        ListIterator<E> li = list.listIterator();
        System.out.print("[");
        while (li.hasNext()){
            System.out.print(li.next() + " ");
        }
        System.out.println("]");
    }
    /***
     * Method to print a list backward
     * @param <E> - Data type being printed
     * @param list - list being printed
     */
    public static <E> void printListBackward(List<E> list){
        ListIterator<E> li = list.listIterator(list.size());
        System.out.print("[");
        while (li.hasPrevious()){
            System.out.print(li.previous() + " ");
        }
        System.out.println("]");
    }
}
