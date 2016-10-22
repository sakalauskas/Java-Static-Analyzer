package analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Collector {

    private static Collector instance;

    public static HashMap<String, List<String>> warnings;

    public static HashMap<String, Integer> stats;

    public static HashMap<String, ComplexityCounter> complexity;


    public Collector() {
        warnings = new HashMap<>();
        stats = new HashMap<>();
        complexity = new HashMap<>();
    }


    /**
     * Add warnings to queue
     *
     * @param className
     * @param warning
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
     * Add complexity results for the class
     *
     * @param className
     * @param counter
     */
    public void addComplexityResults(String className, ComplexityCounter counter) {
        complexity.put(className, counter);
    }

    /**
     * Increment some metric by +1
     *
     * @param metricName
     */
    public void incrementMetric(String metricName) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + 1);
        } else {
            stats.put(metricName, 1);
        }
    }

    /**
     * Increment some metric by specified count
     *
     * @param metricName
     * @param count
     */
    public void incrementMetric(String metricName, int count) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + count);
        } else {
            stats.put(metricName, count);
        }
    }


}
