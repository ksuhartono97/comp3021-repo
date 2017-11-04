import java.math.BigInteger;
import java.util.ArrayList;

// TODO 6: implement the Division operation
// Refer to Subtraction to implement this class
// Note:
//   if there is only one operand, return 1 / the operand (integer division)
//   otherwise divide the first operand by the rest of operands.
// Hint:
//   BigInteger.ONE and BigInteger.divide(BigInteger) are useful

public class Division extends Operation {

    public Division(ArrayList<Expression> operands) {
        super("/", operands);
    }

    public BigInteger eval() {
        BigInteger div = BigInteger.ONE;
        if(operands.size() == 1) {
            return div.divide(operands.get(0).eval());
        }
        else {
            div = operands.get(0).eval();
        }

        for (Expression e : operands.subList( 1, operands.size() )) {
            div = div.divide(e.eval());
        }
        return div;
    }

}