package analyzer.printers;

import analyzer.collectors.HashMapCollector;

/**
 * Created by laurynassakalauskas on 22/10/2016.
 */
public abstract class AbstractPrinter {

    protected HashMapCollector collector;

    public AbstractPrinter(HashMapCollector collector) {

        this.collector = collector;
    }


}
