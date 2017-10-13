package analyzer.visitors;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

public class MetricVisitor extends AbstractVoidVisitorAdapter<Collector> {


    /**
     * Increment classes metric
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration declaration, Collector collector) {

        if (declaration.isInterface()) {
            collector.incrementMetric("Interfaces");
        } else {
            collector.incrementMetric("Classes");


            if (declaration.isFinal()) {
                collector.incrementMetric("Final Classes");
            }
            if (declaration.isAbstract()) {
                collector.incrementMetric("Abstract Classes");
            }

        }

        if (className.endsWith("Test")) {
            collector.incrementMetric("Test Classes");

        }



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

        for (AnnotationExpr annotation: declaration.getAnnotations()) {
            if (annotation.getName().equals("Override"))
                collector.incrementMetric("Overridden Methods");

        }

        if (declaration.toString().startsWith("public")) {
            collector.incrementMetric("Public Methods");
        }

        if (declaration.toString().startsWith("private")) {
            collector.incrementMetric("Private Methods");
        }

        if (declaration.toString().startsWith("protected")) {
            collector.incrementMetric("Protected Methods");
        }

        if (!declaration.hasComment()) {
            collector.incrementMetric("Methods without Javadoc");
        }

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

        collector.incrementMetric("Fields", declaration.getVariables().size());

    }


}
