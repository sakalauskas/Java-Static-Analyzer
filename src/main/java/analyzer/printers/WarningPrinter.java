package analyzer.printers;

import analyzer.collectors.HashMapCollector;

import java.util.List;
import java.util.Map;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class WarningPrinter extends AbstractPrinter implements Printable {

    public WarningPrinter(HashMapCollector collector) {
        super(collector);
    }


    /**
     * Print out warnings to the console
     */
    @Override
    public void print() {
        System.out.println("____________________________________________________________________");
        System.out.println("|---------------------------WARNINGS-------------------------------|");
        System.out.println("|==================================================================|");

        if (collector.getWarnings().entrySet().size() == 0) {

            System.out.println("|No warnings were found. That's awesome!                           |");

        } else {
            System.out.println("|Oh no, there are " + collector.getWarnings().entrySet().size()+ " warnings you need to fix                      |");

        }
        System.out.println("|==================================================================|");

        for (Map.Entry<String, List<String>> entry: collector.getWarnings().entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " warnings");

            for (String warning: entry.getValue()) {

                System.out.println("[WARNING] " + warning);
            }
            System.out.println();
        }



    }
}
