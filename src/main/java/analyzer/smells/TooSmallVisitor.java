package analyzer.smells;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.Collector;
import analyzer.Config;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class TooSmallVisitor extends AbstractVoidVisitorAdapter<Collector> {



    private static final int MIN_VARIABLE_LENGTH = Config.MIN_VARIABLE_LENGTH;
    private static final int MIN_METHOD_NAME = Config.MIN_METHOD_NAME;


    /**
     * Check for too short method names
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {


        if (declaration.getName().toString().length() < MIN_METHOD_NAME)

        super.visit(declaration, collector);

    }


    /**
     * Check for too short variable names
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {

        for (VariableDeclarator variable: declaration.getVars()) {

            if (variable.getId().getName().length() < MIN_VARIABLE_LENGTH) {
                collector.addWarning(className, "Variable \"" + variable.getId().getName() + "\" length is too small");
            }

        }

    }


}
