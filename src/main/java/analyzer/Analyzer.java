package analyzer;

import analyzer.smells.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Analyzer extends SimpleFileVisitor<Path> {
    private Collector collector;

    public Analyzer(Collector collector) {

        this.collector = collector;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        if (isNotJava(file))
            return FileVisitResult.CONTINUE;


        // initialize collector
        ComplexityCounter complexityCounter = new ComplexityCounter(getClassName(file), collector);

        // initialize compilation unit
        CompilationUnit unit = null;
        try {
            unit = JavaParser.parse(file.toFile());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // collect all the stats
        new BooleanMethodVisitor().visit(unit, collector);
        new TooLongVisitor().visit(unit, collector);
        new TooSmallVisitor().visit(unit, collector);
        new VariableNamingConventionVisitor().visit(unit, collector);
        new ClassLineCounterVisitor().visit(unit, collector);
        new ClassComplexityVisitor().visit(unit, complexityCounter);
        new MetricVisitor().visit(unit, collector);

        // print analysis
        complexityCounter.analyze();
        collector.addComplexityResults(getClassName(file), complexityCounter);

        return FileVisitResult.CONTINUE;
    }

    private String getClassName(Path file) {

        String filename = file.getFileName().toString();

        if (filename.indexOf(".") > 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        return filename;
    }

    /**
     * We only need to analyze the Java files
     *
     * @param file
     * @return
     */
    private boolean isNotJava(Path file) {

        return !file.toString().endsWith("java");
    }

}
