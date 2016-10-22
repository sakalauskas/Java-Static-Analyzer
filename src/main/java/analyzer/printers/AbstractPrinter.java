package analyzer.printers;

import analyzer.Collector;

/**
 * Created by laurynassakalauskas on 22/10/2016.
 */
public abstract class AbstractPrinter {

    protected Collector collector;

    public AbstractPrinter(Collector collector) {

        this.collector = collector;
    }


}
