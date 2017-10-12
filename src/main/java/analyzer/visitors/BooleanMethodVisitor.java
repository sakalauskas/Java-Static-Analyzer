package analyzer.visitors;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.Config;
import analyzer.collectors.Collector;
import com.github.javaparser.ast.body.MethodDeclaration;

public class BooleanMethodVisitor extends AbstractVoidVisitorAdapter<Collector> {


    /**
     * Boolean method should start isSomething(), not getSomething()
     *
     * getSomething(someVar) is OK, though
     *
     * @param declaration
     * @param collector
     */
    @Override
    public void visit(MethodDeclaration declaration, Collector collector) {

        if (Config.BOOLEAN_STARTS_WITH_IS && declaration.getType().toString().equals("boolean") && declaration.getParameters().size() == 0) {

            if (!declaration.getNameAsString().startsWith("is")) {

                collector.addWarning(className, "Method name \"" + declaration.getType().toString() + "\"  should start with is, e.g.\"isSomething()\"");

            }
        }

    }


}
