// TODO 6:
// - Define a generic class GraphicsCanvas, which only accepts classes that implement Printable interface
// - The generic class contains the following instance variables and methods
//   - An ArrayList object
//   - add method that adds an element to the array list
//   - get method that returns the element at index i (as specified in the parameter).
//     If the index is out of bound, return null
//   - size method that returns the number of elements in the array list

import java.util.List;
import java.util.ArrayList;

/* Define generic class GraphicsCanvas here */
public class GraphicsCanvas<Printable> {
    private ArrayList<Printable> list = new ArrayList<>();
    public void add (Printable element) {
        list.add(element);
    }
    public Printable get(int index) {
        return list.get(index);
    }
    public int size() {
        return list.size();
    }
}