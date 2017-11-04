import java.util.ArrayList;

// TODO 3: Define the abstract class Operation
// An operation has a operator and multiple operands
// Override the toString method to convert the operation to a string.
// The format is "(operator operand1 operand2 operand3 ...)".
// E.g.
// If the operator is "+", and the operands are "2, 3, 5".
// Then the string representation of this operation is (+ 2 3 5).

public abstract class Operation extends Expression {
    protected String operator;
    protected ArrayList<Expression> operands;

    public Operation (String op, ArrayList<Expression> oper) {
        this.operator = op;
        this.operands = oper;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(operator);
        for(Expression ex : operands) {
            sb.append(" ");
            sb.append(ex.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}