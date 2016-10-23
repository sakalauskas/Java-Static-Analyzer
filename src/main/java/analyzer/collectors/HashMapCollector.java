package analyzer.collectors;

import analyzer.ComplexityCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Collect all stats to hashmap
 *
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class HashMapCollector implements Collector {


    protected static HashMap<String, List<String>> warnings;

    protected static HashMap<String, Integer> stats;

    protected static HashMap<String, ComplexityCounter> complexity;

    public static HashMap<String, List<String>> getWarnings() {
        return warnings;
    }

    public static HashMap<String, Integer> getStats() {
        return stats;
    }

    public static HashMap<String, ComplexityCounter> getComplexity() {
        return complexity;
    }

    public HashMapCollector() {
        warnings = new HashMap<>();
        stats = new HashMap<>();
        complexity = new HashMap<>();
    }


    /**
     * {@inheritDoc}
     */
    public void addWarning(String className, String warning) {

        if (warnings.containsKey(className)) {
            warnings.get(className).add(warning);
        } else {

            warnings.put(className, new ArrayList<String>() {{
                add(warning);
            }});
        }

    }

    /**
     * {@inheritDoc}
     */
    public void addComplexityResults(String className, ComplexityCounter counter) {
        complexity.put(className, counter);
    }

    /**
     * {@inheritDoc}
     */
    public void incrementMetric(String metricName) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + 1);
        } else {
            stats.put(metricName, 1);
        }
    }


    /**
     * {@inheritDoc}
     */
    public void incrementMetric(String metricName, int count) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + count);
        } else {
            stats.put(metricName, count);
        }
    }


}
