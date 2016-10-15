package util;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import smells.ClassComplexityVisitor;
import smells.ClassLineCounter;
import smells.TooLongVisitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Analyzer extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        if (isNotJava(file))
            return FileVisitResult.CONTINUE;


        // initialize collector
        Collector collector = Collector.getInstance();


        // initialize compilation unit
        CompilationUnit unit = null;
        try {
            unit = JavaParser.parse(file.toFile());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(getClassName(file));

        // collect stats about too long names
        new TooLongVisitor().visit(unit, collector);
        // collect stats about
        new ClassLineCounter().visit(file);
        // collect stats about class complexity
        new ClassComplexityVisitor().visit(unit, new ComplexityCounter(getClassName(file)));


        return FileVisitResult.CONTINUE;
    }

    private String getClassName(Path file) {
        return file.toString();
    }

    private boolean isNotJava(Path file) {
        return !getClassName(file).endsWith("java");
    }

}
