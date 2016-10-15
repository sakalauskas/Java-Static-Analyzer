import org.fusesource.jansi.AnsiConsole;
import analyzer.Analyzer;
import analyzer.printers.ComplexityPrinter;
import analyzer.printers.MetricPrinter;
import analyzer.printers.WarningPrinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Runner {


    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall();

        String path = "/Users/laurynassakalauskas/IdeaProjects/CS409/testcases/javaparser";

        run(path);

        AnsiConsole.systemUninstall();
    }

    private static void run(String path) {
        run(Paths.get(path));
    }

    /**
     * Walk through the files and collect stats and then print them out
     *
     * @param p
     */
    private static void run(Path p) {
        try {
            Files.walkFileTree(p, new Analyzer());

            new WarningPrinter().print();
            new MetricPrinter().print();
            new ComplexityPrinter().print();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
