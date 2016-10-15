package analyzer.smells;

import analyzer.AbstractVoidVisitorAdapter;
import analyzer.Collector;
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

        if (declaration.getType().toString().equals("boolean") && declaration.getParameters().size() == 0) {

            if (!declaration.getName().startsWith("is")) {

                collector.addWarning(className, "Method name \"" + declaration.getType().toString() + "\"  should start with is, e.g.\"isSomething()\"");

            }
        }

    }


}
