package analyzer.printers;

import analyzer.Collector;
import analyzer.ComplexityCounter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class ComplexityPrinter implements  Printable{


    @Override
    public void print() {
        HashMap<String,Integer> totalUsage = new HashMap<>();

        for (Map.Entry<String, ComplexityCounter> entry: Collector.complexity.entrySet()) {

            for (Map.Entry<String, Integer> e: entry.getValue().getUsage().entrySet()) {
                totalUsage.merge(e.getKey(), e.getValue(), Integer::sum);
            }

        }

        System.out.println("_________________________________");
        System.out.println("|----------Complexity-----------|");
        System.out.println("|===============================|");
        System.out.format("|%15s|%15s|\n", "Type", "Count");
        System.out.println("|===============================|");

        for (Map.Entry<String, Integer> entry: totalUsage.entrySet()) {

            System.out.format("|%15s|%15s|\n", entry.getKey(), entry.getValue());

        }
        System.out.println("|===============================|");
    }
}
