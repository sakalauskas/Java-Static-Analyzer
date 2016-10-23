import analyzer.Analyzer;
import analyzer.collectors.HashMapCollector;
import analyzer.printers.ComplexityPrinter;
import analyzer.printers.MetricPrinter;
import analyzer.printers.WarningPrinter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Runner {


    /**
     * Simple Analyzer runner method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        System.out.println("Please type full path to project directory you want to analyze.");

        String tempPath =  in.nextLine();
//        String tempPath =  "/Users/laurynassakalauskas/IdeaProjects/CS409/testcases/java-design-patterns";
        try {
            run(tempPath);
        } catch (Exception e) {
            System.out.println("Looks like you specified not existing directory. Exiting...");
        }


//        other way of using
//        if (args.length == 0) {
//            System.out.println("Usage: java -jar analyzer [path]");
//        } else {
//            String path = args[0];
//
//            run(path);
//        }

    }

    /**
     * Convert String to path if needed
     *
     * @param path
     */
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
            HashMapCollector collector = new HashMapCollector();

            Files.walkFileTree(p, new Analyzer(collector));

            new WarningPrinter(collector).print();
            new MetricPrinter(collector).print();
            new ComplexityPrinter(collector).print();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
