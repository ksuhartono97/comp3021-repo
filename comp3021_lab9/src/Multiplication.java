import java.math.BigInteger;
import java.util.ArrayList;

// TODO 4: implement the Multiplication operation
// Refer the Addition to see how to implement this class
// Hint: use the constant BigInteger.ONE and the method BigInteger.multiply(BigInteger)
//       to implement the eval() method

public class Multiplication extends Operation {

    public Multiplication(ArrayList<Expression> operands) {
        super("*", operands);
    }

    public BigInteger eval() {
        BigInteger mult = BigInteger.ONE;
        for (Expression e : operands) {
            mult = mult.multiply(e.eval());
        }
        return mult;
    }

}
