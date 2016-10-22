package analyzer.printers;

import analyzer.Collector;

import java.util.Map;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class MetricPrinter extends AbstractPrinter implements Printable {

    public MetricPrinter(Collector collector) {
        super(collector);
    }

    @Override
    public void print() {
        System.out.println("_________________________________");
        System.out.println("|------------METRICS------------|");
        System.out.println("|===============================|");
        System.out.format("|%25s|%5s|\n", "Type", "Count");
        System.out.println("|===============================|");

        for (Map.Entry<String, Integer> entry: collector.stats.entrySet()) {

            System.out.format("|%25s|%5s|\n", entry.getKey(), entry.getValue());
        }
    }
}
