package analyzer.visitors;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.ComplexityCounter;

public class ConditinalOperatorComplexityVisitor extends AbstractVoidVisitorAdapter<ComplexityCounter> {

	private String methodName;

	public ConditinalOperatorComplexityVisitor(String methodName) {
		super();
		this.methodName = methodName;
	}

	@Override
	public void visit(BinaryExpr expr, ComplexityCounter counter) {
		Operator operator = expr.getOperator();
		switch (operator) {
		case BINARY_AND:
			counter.add(methodName, "BITWISE_AND_OPERATOR");
			break;
		case BINARY_OR:
			counter.add(methodName, "BITWISE_OR_OPERATOR");
			break;
		case AND:
			counter.add(methodName, "AND_OPERATOR");
			break;
		case OR:
			counter.add(methodName, "OR_OPERATOR");
			break;
		default:
			break;
		}
		super.visit(expr, counter);
	}

}
