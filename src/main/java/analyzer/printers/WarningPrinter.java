package analyzer.printers;

import analyzer.Collector;

import java.util.List;
import java.util.Map;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class WarningPrinter extends AbstractPrinter implements Printable {

    public WarningPrinter(Collector collector) {
        super(collector);
    }

    @Override
    public void print() {
        System.out.println("_________________________________");
        System.out.println("|------------WARNINGS-----------|");
        System.out.println("|===============================|");

        for (Map.Entry<String, List<String>> entry: collector.warnings.entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " warnings");

            for (String warning: entry.getValue()) {

                System.out.println(ansi().fg(RED).a("[WARNING]" ).reset().a(" " + warning));
            }
            System.out.println();
        }

        if (collector.warnings.entrySet().size() == 0) {

            System.out.println("|No warnings were found. Success|");
            System.out.println("|===============================|");
        }
    }
}
