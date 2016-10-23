package analyzer.visitors;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.ComplexityCounter;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class ClassComplexityVisitor extends AbstractVoidVisitorAdapter<ComplexityCounter> {

    protected String methodName;

    /**
     * Before visiting any method, mark the method name, so we could collect it later.
     *
     * @param declaration
     * @param counter
     */
    public void visit(MethodDeclaration declaration, ComplexityCounter counter) {

        methodName = declaration.getName();

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

        if(statement.getElseStmt() != null) {
            counter.add(methodName, "ELSE");
        }


        String condition = statement.getCondition().toString();

        regexCheck(condition, Pattern.compile("/(\\s|\\w|\\d)&(\\s|\\w|\\d)/xg"),  "BITWISE_AND_OPERATOR",  counter);
        regexCheck(condition, Pattern.compile("/(\\s|\\w|\\d)\\|(\\s|\\w|\\d)/xg"),  "BITWISE_OR_OPERATOR",   counter);
        regexCheck(condition, Pattern.compile("/(\\s|\\w|\\d)&&(\\s|\\w|\\d)/xg"), "AND_OPERATOR",          counter);
        regexCheck(condition, Pattern.compile("/(\\s|\\w|\\d)\\|\\|(\\s|\\w|\\d)/xg"), "OR_OPERATOR",           counter);


        super.visit(statement, counter);
    }


    private void regexCheck(String haystack, Pattern pattern, String type, ComplexityCounter counter) {
        Matcher matcher = pattern.matcher(haystack);

        while (matcher.find()) {
            counter.add(methodName, type);
        }
    }

    /**
     * Count switch statements
     *
     * @param statement
     * @param counter
     */
    @Override
    public void visit(SwitchEntryStmt statement, ComplexityCounter counter) {


        for (Statement st :statement.getStmts()) {

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
     * Count try statements
     *
     * @param statement
     * @param counter
     */
    @Override
    public void visit(TryStmt statement, ComplexityCounter counter) {

        counter.add(methodName, "TRY");

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

        super.visit(statement, counter);
    }
}
