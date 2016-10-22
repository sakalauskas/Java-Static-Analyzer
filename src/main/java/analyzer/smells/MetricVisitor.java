package analyzer.smells;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.Collector;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class MetricVisitor extends AbstractVoidVisitorAdapter<Collector> {


    protected String methodName;

    /**
     * Increment classes metric
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Collector collector) {


        collector.incrementMetric("Classes");


        super.visit(declaration, collector);
    }


    /**
     * Increment method and parameter count
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {

        collector.incrementMetric("Methods");

        collector.incrementMetric("Parameters", declaration.getParameters().size());

        super.visit(declaration, collector);

    }


    /**
     * Increment field count
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(VariableDeclarationExpr declaration, Collector collector) {

        collector.incrementMetric("Fields", declaration.getVars().size());

    }


}
