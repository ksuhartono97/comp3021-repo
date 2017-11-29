// TODO 1:
// - Define a Printable interface, which has a single public abstract method print
// - print is a no-arg abstract method and returns nothing
// - Note: You are required to add the annotation so that Java compiler would check whether
//   the interface is SAM or not

@FunctionalInterface
public interface Printable {
    public void print();
}