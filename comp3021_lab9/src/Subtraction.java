import java.math.BigInteger;
import java.util.ArrayList;

// TODO 5: implement the Subtraction operation
// Refer to Addition to implement this class
// Note:
//   if number of operand is 1, return the negation
//   otherwise, subtract the rest of operands from the first operand.
// Hint: BigInteger.negate() and BigInteger.subtract(BigInteger) are useful to implement eval()

public class Subtraction extends Operation {

    public Subtraction(ArrayList<Expression> operands) {
        super("-", operands);
    }

    public BigInteger eval() {
        BigInteger sum = BigInteger.ZERO;
        if(operands.size() == 1) {
            return operands.get(0).eval().negate();
        }
        else {
            sum = operands.get(0).eval();
        }

        for (Expression e : operands.subList( 1, operands.size() )) {
            sum = sum.subtract(e.eval());
        }
        return sum;
    }

}