package util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class AbstractVoidVisitorAdapter<A> extends VoidVisitorAdapter<A> {

    protected String className;

    @Override
    public void visit(CompilationUnit cu, A arg) {

        className = cu.getClass().toString();


        super.visit(cu, arg);
    }
}
