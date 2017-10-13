package analyzer;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Wrapper class for automatically resolving classname and reusing throughout the visitor
 *
 * Created by laurynassakalauskas on 15/10/2016.
 */
public abstract class AbstractVoidVisitorAdapter<A> extends VoidVisitorAdapter<A> {

    protected String className;

    @Override
    public void visit(CompilationUnit cu, A arg) {

        if (cu.getTypes().size() > 0) {
            className = cu.getTypes().get(0).getNameAsString();
        } else {
            className = "Unknown class";
        }


        super.visit(cu, arg);
    }
}
