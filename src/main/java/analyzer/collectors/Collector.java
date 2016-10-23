package analyzer.collectors;

import analyzer.ComplexityCounter;

/**
 * Created by laurynassakalauskas on 23/10/2016.
 */
public interface Collector {

    /**
     * Add warnings to queue
     *
     * @param className
     * @param warning
     */
    void addWarning(String className, String warning);

    /**
     * Add complexity results for the class
     *
     * @param className
     * @param counter
     */
    void addComplexityResults(String className, ComplexityCounter counter);

    /**
     * Increment some metric by +1
     *
     * @param metricName
     */
    void incrementMetric(String metricName);

    /**
     * Increment some metric by specified count
     *
     * @param metricName
     * @param count
     */
     void incrementMetric(String metricName, int count);
}
