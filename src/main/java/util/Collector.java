package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by laurynassakalauskas on 15/10/2016.
 */
public class Collector {

    private static Collector instance;

    private static HashMap<String, List<String>> warnings;


    private Collector() {
        warnings = new HashMap<>();
    }


    public static Collector getInstance() {
        if(instance == null) {
            instance = new Collector();
        }


        return instance;
    }

    public void addWarning(String className, String warning) {

        if (warnings.containsKey(className)) {
            warnings.get(className).add(warning);
        } else {

            warnings.put(className, new ArrayList<String>() {{
                add(warning);
            }});
        }


        System.out.println(ansi().fg(RED).a("[WARNING]").reset().a(" " + warning));

    }

    public void addInfo(String className, String warning) {

    }
}
