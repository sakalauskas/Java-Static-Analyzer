package analyzer.printers;

import analyzer.Collector;
import analyzer.ComplexityCounter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class ComplexityPrinter extends AbstractPrinter implements  Printable{


    public ComplexityPrinter(Collector collector) {
        super(collector);
    }

    @Override
    public void print() {
        HashMap<String,Integer> totalUsage = new HashMap<>();

        int totalWCM = 0;

        for (Map.Entry<String, ComplexityCounter> entry: collector.complexity.entrySet()) {

            totalWCM += entry.getValue().weightedMethodCount();
            for (Map.Entry<String, Integer> e: entry.getValue().getUsage().entrySet()) {
                totalUsage.merge(e.getKey(), e.getValue(), Integer::sum);
            }

        }

        totalWCM /= collector.complexity.entrySet().size();

        System.out.println("|===============================|");
        System.out.println("|----------Complexity-----------|");
        System.out.println("|===============================|");
        System.out.format("|%25s|%5s|\n", "Type", "Count");
        System.out.println("|===============================|");

        for (Map.Entry<String, Integer> entry: totalUsage.entrySet()) {

            System.out.format("|%25s|%5s|\n", entry.getKey(), entry.getValue());

        }
        System.out.println("|===============================|");
        System.out.println("|----Weighted method count------|");
        System.out.println("|===============================|");
        System.out.format("|%25s|%5s|\n", "Class", "Count");
        System.out.println("|===============================|");
        for (Map.Entry<String, ComplexityCounter> entry: collector.complexity.entrySet()) {

            System.out.format("|%25s|%5s|\n", entry.getKey(), entry.getValue().weightedMethodCount());


        }
        System.out.println("Average weighted method count: "  +totalWCM);
    }
}
