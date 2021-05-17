package analyzer.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.ForeachStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchEntryStmt;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.ComplexityCounter;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class ClassComplexityVisitor extends AbstractVoidVisitorAdapter<ComplexityCounter> {

	protected String methodName;

	/**
	 * Before visiting any method, mark the method name, so we could collect it
	 * later.
	 *
	 * @param declaration
	 * @param counter
	 */
	public void visit(MethodDeclaration declaration, ComplexityCounter counter) {

		methodName = declaration.getNameAsString();

		super.visit(declaration, counter);
	}

	/**
	 * Count foreach statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(ForeachStmt statement, ComplexityCounter counter) {
		counter.add(methodName, "FOREACH");

		super.visit(statement, counter);
	}

	/**
	 * Count for statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(ForStmt statement, ComplexityCounter counter) {
		counter.add(methodName, "FOR");

		super.visit(statement, counter);
	}

	/**
	 * Count if/else statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(IfStmt statement, ComplexityCounter counter) {
		counter.add(methodName, "IF");

//		The else should not count. this block cause double count on elseif block.
//		if (statement.getElseStmt() != null) {
//			counter.add(methodName, "ELSE");
//		}

		analyzeCondition(statement.getCondition(), counter);

		super.visit(statement, counter);
	}

	/**
	 * Count switch statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(SwitchEntryStmt statement, ComplexityCounter counter) {

		for (@SuppressWarnings("unused") Statement st : statement.getStatements()) {

			counter.add(methodName, "SWITCH");

		}

		super.visit(statement, counter);
	}

	/**
	 * Count throw statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(ThrowStmt statement, ComplexityCounter counter) {

		counter.add(methodName, "THROW");

		super.visit(statement, counter);
	}


	/**
	 * Count catch statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(CatchClause statement, ComplexityCounter counter) {

		counter.add(methodName, "CATCH");

		super.visit(statement, counter);
	}

	/**
	 * Count while statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(WhileStmt statement, ComplexityCounter counter) {

		counter.add(methodName, "WHILE");

		analyzeCondition(statement.getCondition(), counter);

		super.visit(statement, counter);
	}

	/**
	 * Count continue statements
	 *
	 * @param statement
	 * @param counter
	 */
	@Override
	public void visit(ContinueStmt statement, ComplexityCounter counter) {
		counter.add(methodName, "CONTINUE");
		super.visit(statement, counter);
	}

	
	/**
	 * Count condition expression
	 *
	 * @param expression
	 * @param counter
	 */
	@Override
	public void visit(ConditionalExpr expression, ComplexityCounter counter) {
		counter.add(methodName, "CONDITIONAL");
		analyzeCondition(expression.getCondition(), counter);

		super.visit(expression, counter);
	}

	private void analyzeCondition(Expression condition, ComplexityCounter counter) {

		if (condition.getMetaModel().getType().equals(BinaryExpr.class)) {

			new ConditinalOperatorComplexityVisitor(methodName).visit((BinaryExpr) condition, counter);

		}
	}
}
