import analyzer.Analyzer;
import analyzer.Collector;
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


    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(System.in);

        System.out.println("Please type the project directory you want to analyze.");

        String tempPath =  in.nextLine(); // "/Users/laurynassakalauskas/IdeaProjects/CS409/testcases/javaparser";
        run(tempPath);



//        if (args.length == 0) {
//            System.out.println("Usage: java -jar analyzer [path]");
//        } else {
//            String path = args[0];
//
//            run(path);
//        }

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
            Collector collector = new Collector();

            Files.walkFileTree(p, new Analyzer(collector));

            new WarningPrinter(collector).print();
            new MetricPrinter(collector).print();
            new ComplexityPrinter(collector).print();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
