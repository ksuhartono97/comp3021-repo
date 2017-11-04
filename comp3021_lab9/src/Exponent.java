import java.math.BigInteger;
import java.util.ArrayList;

// TODO 7: implement the Exponent operation
// Hint: BigInteger.pow(int) is useful when implementing eval()
public class Exponent extends Operation {

    public Exponent(ArrayList<Expression> operands) {
        super("^", operands);
    }

    public BigInteger eval() {
        BigInteger base = operands.get(0).eval();
        BigInteger power = operands.get(1).eval();

        return base.pow(power.intValue());
    }

}