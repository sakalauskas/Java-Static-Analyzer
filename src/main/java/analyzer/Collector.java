package analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Collector {

    private static Collector instance;

    public static HashMap<String, List<String>> warnings;

    public static HashMap<String, Integer> stats;

    public static HashMap<String, ComplexityCounter> complexity;


    private Collector() {
        warnings = new HashMap<>();
        stats = new HashMap<>();
        complexity = new HashMap<>();
    }


    public static Collector getInstance() {
        if(instance == null) {
            instance = new Collector();
        }

        return instance;
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
     * Add warnings to queue
     *
     * @param className
     * @param warning
     */
    public void addComplexityResults(String className, ComplexityCounter counter) {
        complexity.put(className, counter);
    }

    public void incrementMetric(String metricName) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + 1);
        } else {
            stats.put(metricName, 1);
        }
    }

    public void incrementMetric(String metricName, int count) {
        if (stats.containsKey(metricName)) {
            stats.put(metricName, stats.get(metricName) + count);
        } else {
            stats.put(metricName, count);
        }
    }

    public void printWarningsByClass() {
        System.out.println("_________________________________");
        System.out.println("|------------WARNINGS-----------|");
        System.out.println("|===============================|");

        for (Map.Entry<String, List<String>> entry: warnings.entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " warnings");

            for (String warning: entry.getValue()) {

                System.out.println(ansi().fg(RED).a("[WARNING]" ).reset().a(" " + warning));
            }
            System.out.println();
        }

        if (warnings.entrySet().size() == 0) {

            System.out.println("|No warnings were found. Success|");
            System.out.println("|===============================|");
        }
    }


    private void printComplexityErrorsAndUsage() {

        HashMap<String,Integer> totalUsage = new HashMap<>();

        for (Map.Entry<String, ComplexityCounter> entry: complexity.entrySet()) {

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
