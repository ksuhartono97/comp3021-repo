import java.math.BigInteger;

// TODO 2: define the Number class
// It is a subtype of Expression, and it is comparable
// Override the toString() method to convert the member val to a string.
public class Number extends Expression implements Comparable<Number> {
    private BigInteger value;

    public Number (BigInteger num) {
        this.value = num;
    }

    @Override
    public BigInteger eval() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(Number o) {
        if(o instanceof Number) {
            return ((Number)o).value.compareTo(this.value);
        }
        return 0;
    }
}